package com.santosediego.VidasPorVidas.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.Instant;
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

import com.opencsv.CSVWriter;
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
		String filterConditions = Normalizer.normalize(conditions, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "").toLowerCase();


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

	// Provide file CSV
	public Resource provideFileCSV() {

		// String fileName = "doadores.csv";
		Path directory = createDirectory();
		writeDataInFileCSV(directory + "/" + fileName);

		try {
			Files.list(directory).forEach(file -> {
				if (file.getFileName().toString().startsWith(fileName)) {
					foundFile = file;
					return;
				}
			});

			if (foundFile != null) {
				return new UrlResource(foundFile.toUri());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// Create directory for file
	private Path createDirectory() {
		Path directory = Paths.get("export");

		try {
			if (!Files.exists(directory)) {
				Files.createDirectories(directory);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return directory;
	}

	// Write data in file CSV
	private void writeDataInFileCSV(String filePath) {

		File file = new File(filePath);

		try {
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

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create file header CSV
	private String[] createHeader() {
		return new String[] {
				"Id", "Nome", "CPF", "RG", "Data Nascimento", "Genero", "Estado Cívil", "Grupo Sanguíneo"
				, "Celular", "Peso", "Logradouro", "Número", "Complemento", "Bairro", "CEP", "Localidade"
				, "Estado", "Data Cadastro", "Data Alteração"	
		};
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
				(doador.getDataAlteracao() == null) ? "" : doador.getDataAlteracao().toString(),
		};
	}
}