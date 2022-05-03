package com.santosediego.VidasPorVidas.services;

import java.time.Instant;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santosediego.VidasPorVidas.dto.DoadorDTO;
import com.santosediego.VidasPorVidas.entities.Doador;
import com.santosediego.VidasPorVidas.entities.Endereco;
import com.santosediego.VidasPorVidas.entities.enums.EstadoCivil;
import com.santosediego.VidasPorVidas.entities.enums.GrupoSanguineo;
import com.santosediego.VidasPorVidas.repositories.DoadorRepository;
import com.santosediego.VidasPorVidas.repositories.EnderecoRepository;

@Service
public class DoadorService {

	@Autowired
	private DoadorRepository doadorRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional(readOnly = true)
	public Page<DoadorDTO> findAll(Pageable pegeable) {
		Page<Doador> result = doadorRepository.findAll(pegeable);
		Page<DoadorDTO> page = result.map(x -> new DoadorDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public DoadorDTO findById(Long id) {
		Doador doador = doadorRepository.findById(id).get();

		return new DoadorDTO(doador);
	}

	@Transactional
	public DoadorDTO insert(DoadorDTO dto) {
		try {
			Doador doador = new Doador();
			Endereco endereco = new Endereco();

			doador.setId(null);
			endereco.setId(null);

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
			doador.setDataCadastro(Instant.now());

			endereco.setLogradouro(dto.getLogradouro());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setBairro(dto.getBairro());
			endereco.setCep(dto.getCep());
			endereco.setLocalidade(dto.getLocalidade());
			endereco.setEstado(dto.getEstado());
			doador.setEndereco(endereco);

			doador = doadorRepository.save(doador);

			endereco.setDoador(doador);
			endereco = enderecoRepository.save(endereco);

			return new DoadorDTO(doador);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Id exists");
		}
	}

	@Transactional
	public DoadorDTO update(Long id, DoadorDTO dto) {

		try {

			Doador doador = doadorRepository.getById(id);
			Endereco endereco = enderecoRepository.getById(doador.getEndereco().getId());

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
			doador.setDataAlteracao(Instant.now());

			endereco.setLogradouro(dto.getLogradouro());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setBairro(dto.getBairro());
			endereco.setCep(dto.getCep());
			endereco.setLocalidade(dto.getLocalidade());
			endereco.setEstado(dto.getEstado());

			doador = doadorRepository.save(doador);
			endereco = enderecoRepository.save(endereco);

			return new DoadorDTO(doador);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Integrity violation");
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			Optional<Doador> obj = doadorRepository.findById(id);
			Doador doador = obj.orElseThrow(() -> new OpenApiResourceNotFoundException("Entity not found"));
			enderecoRepository.deleteById(doador.getEndereco().getId());
			doadorRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new OpenApiResourceNotFoundException("I not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Integrity violation");
		}
	}

}