package com.santosediego.VidasPorVidas.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.santosediego.VidasPorVidas.dto.DoadorDTO;
import com.santosediego.VidasPorVidas.dto.DoadorExportDTO;
import com.santosediego.VidasPorVidas.entities.Doador;
import com.santosediego.VidasPorVidas.entities.Endereco;
import com.santosediego.VidasPorVidas.entities.enums.EstadoCivil;
import com.santosediego.VidasPorVidas.entities.enums.GrupoSanguineo;
import com.santosediego.VidasPorVidas.repositories.DoadorRepository;
import com.santosediego.VidasPorVidas.repositories.EnderecoRepository;
import com.santosediego.VidasPorVidas.services.exceptions.DataIntegrityException;
import com.santosediego.VidasPorVidas.services.exceptions.DatabaseException;
import com.santosediego.VidasPorVidas.services.exceptions.ResourceIOException;
import com.santosediego.VidasPorVidas.services.exceptions.ResourceNotFoundException;

@Service
public class DoadorService {

	private String fileName = "doadores.csv";
	private Path foundFile;

	@Autowired
	private DoadorRepository doadorRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional(readOnly = true)
	public Page<DoadorDTO> findAll(String conditions, Pageable pegeable) {

		// Filters what the user has typed, stripping dots and converting to lowercase
		String filterConditions = Normalizer.normalize(conditions, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				.toLowerCase();

		Page<Doador> result = doadorRepository.findDoadores(filterConditions, pegeable);
		Page<DoadorDTO> page = result.map(x -> new DoadorDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public DoadorDTO findById(Long id) {
		Optional<Doador> obj = doadorRepository.findById(id);
		Doador doador = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));

		return new DoadorDTO(doador);
	}

	@Transactional(readOnly = true)
	public List<DoadorExportDTO> exportAll() {
		List<Doador> doador = doadorRepository.findAll();
		List<DoadorExportDTO> listDTO = doador.stream().map(x -> new DoadorExportDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Transactional
	public DoadorDTO insert(DoadorDTO dto) {

		try {

			Doador doador = new Doador();
			Endereco endereco = new Endereco();

			doador.setId(null);
			endereco.setId(null);

			copyDtoToEntity(dto, doador, endereco);

			doador.setEndereco(endereco);

			doador = doadorRepository.save(doador);

			endereco.setDoador(doador);

			endereco = enderecoRepository.save(endereco);

			return new DoadorDTO(doador);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("CPF já cadastrado!");
		}
	}

	@Transactional
	public DoadorDTO update(Long id, DoadorDTO dto) {

		try {
			Optional<Doador> obj = doadorRepository.findById(id);
			Doador doador = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
			Endereco endereco = doador.getEndereco();

			copyDtoToEntity(dto, doador, endereco);

			doador = doadorRepository.saveAndFlush(doador);
			endereco = enderecoRepository.saveAndFlush(endereco);

			return new DoadorDTO(doador);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Violação de integridade", e);
		}
	}

	public void delete(Long id) {
		try {
			Optional<Doador> obj = doadorRepository.findById(id);
			Doador doador = obj.orElseThrow(() -> new OpenApiResourceNotFoundException("Entity not found"));
			enderecoRepository.deleteById(doador.getEndereco().getId());
			doadorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}

	// Provide file CSV
	public Resource provideFileCSV() {

		try {
			Path directory = createDirectory("export");
			writeDataInFileCSV(directory + "/" + fileName);

			Files.list(directory).forEach(file -> {
				if (file.getFileName().toString().startsWith(fileName)) {
					foundFile = file;
					return;
				}
			});

			return new UrlResource(foundFile.toUri());
		} catch (IOException e) {
			throw new ResourceIOException(e.getMessage());
		}
	}

	public List<DoadorDTO> uploadFile(MultipartFile multipartFile) {

		if (!multipartFile.isEmpty() && multipartFile.getContentType().equals("text/csv") || multipartFile.getName().equals("doadores")) {
			
			List<DoadorDTO> listDto = new ArrayList<DoadorDTO>();
			Path filePath = saveFile(multipartFile);

			if (filePath != null)
				listDto = importData(filePath);

			return listDto;
		} else {
			throw new ResourceIOException("Arquivo incompatível!");
		}
	}

	private void copyDtoToEntity(DoadorDTO dto, Doador doador, Endereco endereco) {

		doador.setNome(dto.getNome());
		doador.setCpf(dto.getCpf());
		doador.setRg(dto.getRg());
		doador.setDataNascimento(dto.getDataNascimento());
		doador.setGenero(dto.getGenero());
		doador.setEstadoCivil(EstadoCivil.toEnum(dto.getEstadoCivil()));
		doador.setGrupoSanquineo(GrupoSanguineo.toEnum(dto.getGrupoSanguineo()));
		doador.setCelular(dto.getCelular());
		doador.setTelefone(dto.getTelefone());
		doador.setPeso(dto.getPeso());

		if (doador.getId() == null) {
			doador.setDataCadastro(Instant.now());
		} else {
			doador.setDataAlteracao(Instant.now());
		}

		endereco.setLogradouro(dto.getLogradouro());
		endereco.setNumero(dto.getNumero());
		endereco.setComplemento(dto.getComplemento());
		endereco.setBairro(dto.getBairro());
		endereco.setCep(dto.getCep());
		endereco.setLocalidade(dto.getLocalidade());
		endereco.setEstado(dto.getEstado());
	}

	// Write data in file CSV
	private void writeDataInFileCSV(String filePath) {

		try {
			File file = new File(filePath);

			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter with ';' as separator
			CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			// adding header to csv
			writer.writeNext(createHeader());

			// add data to csv
			List<Doador> doadores = doadorRepository.findAll();

			doadores.forEach(doador -> {
				writer.writeNext(createLine(doador));
			});

			// closing writer connection
			writer.close();

		} catch (IOException e) {
			throw new ResourceIOException(e.getMessage());
		}
	}

	// Create file header CSV
	private String[] createHeader() {
		return new String[] { "Id", "Nome", "CPF", "RG", "Data Nascimento", "Gênero", "Estado Civil", "Grupo Sanguíneo",
				"Celular", "Peso", "Logradouro", "Número", "Complemento", "Bairro", "CEP", "Localidade", "Estado",
				"Data Cadastro", "Data Alteração" };
	}

	// Create file line CSV
	private String[] createLine(Doador doador) {
		return new String[] {
				(doador.getId() == null) ? "" : doador.getId().toString(),
				(doador.getNome() == null) ? "" : doador.getNome(),
				(doador.getCpf() == null) ? "" : doador.getCpf(),
				(doador.getRg() == null) ? "" : doador.getRg(),
				(doador.getDataNascimento() == null) ? "" : doador.getDataNascimento().toString(),
				(doador.getGenero() == null) ? "" : doador.getGenero(),
				(doador.getEstadoCivil() == null) ? "" : doador.getEstadoCivil().getId(),
				(doador.getGrupoSanquineo() == null) ? "" : doador.getGrupoSanquineo().getId(),
				(doador.getCelular() == null) ? "" : doador.getCelular(),
				(doador.getPeso() == null) ? "" : doador.getPeso().toString(),
				(doador.getEndereco().getLogradouro() == null) ? "" : doador.getEndereco().getLogradouro(),
				(doador.getEndereco().getNumero() == null) ? "" : doador.getEndereco().getNumero(),
				(doador.getEndereco().getComplemento() == null) ? "" : doador.getEndereco().getComplemento(),
				(doador.getEndereco().getBairro() == null) ? "" : doador.getEndereco().getBairro(),
				(doador.getEndereco().getCep() == null) ? "" : doador.getEndereco().getCep(),
				(doador.getEndereco().getLocalidade() == null) ? "" : doador.getEndereco().getLocalidade(),
				(doador.getEndereco().getEstado() == null) ? "" : doador.getEndereco().getEstado(),
				(doador.getDataCadastro() == null) ? "" : doador.getDataCadastro().toString(),
				(doador.getDataAlteracao() == null) ? "" : doador.getDataAlteracao().toString()
		};
	}

	// Create directory for file
	private Path createDirectory(String nameDirectory) {

		try {
			Path directory = Paths.get(nameDirectory);

			if (!Files.exists(directory)) {
				Files.createDirectories(directory);
			}

			return directory;
		} catch (IOException e) {
			throw new ResourceIOException(e.getMessage());
		}
	}

	// Save file in internal directory
	private Path saveFile(MultipartFile multipartFile) {

		Path directory = createDirectory("import");
		String fileName = multipartFile.getOriginalFilename();

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = directory.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return filePath;
		} catch (IOException e) {
			throw new ResourceIOException(e.getMessage());
		}
	}

	@Transactional
	private List<DoadorDTO> importData(Path filePath) {

		try {

			List<DoadorDTO> listDto = new ArrayList<DoadorDTO>();

			// Create an object of filereader class with CSV file as a parameter.
			FileReader filereader = new FileReader(filePath.toString());

			// create csvParser object with custom separator semi-colon
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

			// create csvReader object passing file reader as a parameter
			CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();
			for (String[] row : allData) {
				for (String cell : row) {

					String[] vect = cell.split(",");
					Optional<Doador> obj = doadorRepository.findByCpf(vect[2]);

					if (obj.isEmpty()) {
						DoadorDTO dto = new DoadorDTO(null, vect[1], vect[2], vect[3], Instant.parse(vect[4]), vect[5],
								vect[6], vect[7], vect[8], null, Double.parseDouble(vect[9]), vect[10], vect[11],
								vect[12], vect[13], vect[14], vect[15], vect[16]);

						dto = insert(dto);
						listDto.add(dto);
					}
				}
			}
			return listDto;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(e.getMessage());
		} catch (FileNotFoundException e) {
			throw new ResourceIOException(e.getMessage());
		} catch (IOException e) {
			throw new ResourceIOException(e.getMessage());
		} catch (CsvValidationException e) {
			throw new ResourceIOException(e.getMessage());
		} catch (CsvException e) {
			throw new ResourceIOException(e.getMessage());
		}
	}
}