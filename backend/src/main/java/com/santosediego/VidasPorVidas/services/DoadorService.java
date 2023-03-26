package com.santosediego.VidasPorVidas.services;

import com.santosediego.VidasPorVidas.dto.DoadorDTO;
import com.santosediego.VidasPorVidas.entities.Doador;
import com.santosediego.VidasPorVidas.entities.Endereco;
import com.santosediego.VidasPorVidas.entities.enums.EstadoCivil;
import com.santosediego.VidasPorVidas.entities.enums.GrupoSanguineo;
import com.santosediego.VidasPorVidas.repositories.DoadorRepository;
import com.santosediego.VidasPorVidas.repositories.EnderecoRepository;
import com.santosediego.VidasPorVidas.services.exceptions.DataIntegrityException;
import com.santosediego.VidasPorVidas.services.exceptions.DatabaseException;
import com.santosediego.VidasPorVidas.services.exceptions.ResourceNotFoundException;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoadorService {

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
    public List<DoadorDTO> listAll() {
        List<Doador> doador = doadorRepository.findAll();
        List<DoadorDTO> listDTO = doador.stream().map(x -> new DoadorDTO(x)).collect(Collectors.toList());
        return listDTO;
    }

    @Transactional
    public DoadorDTO insert(DoadorDTO dto) {

        try {
            Doador doador = new Doador();
            doador.setId(null);

            copyDtoToEntity(dto, doador);

            doador = doadorRepository.save(doador);
            return new DoadorDTO(doador);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("CPF já cadastrado!");
        }
    }

    @Transactional
    public DoadorDTO update(Long id, DoadorDTO dto) {

        try {
            Optional<Doador> obj = doadorRepository.findById(id);
            Doador doador = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado " + id));

            copyDtoToEntity(dto, doador);

            doador = doadorRepository.saveAndFlush(doador);
            return new DoadorDTO(doador);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Violação de integridade", e);
        }
    }

    public void delete(Long id) {
        try {
            doadorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    private void copyDtoToEntity(DoadorDTO dto, Doador doador) {

        Endereco endereco = new Endereco();

        doador.setNome(dto.getNome());
        doador.setCpf(dto.getCpf());
        doador.setRg(dto.getRg());
        doador.setDataNascimento(dto.getDataNascimento());
        doador.setGenero(dto.getGenero());
        doador.setEstadoCivil(EstadoCivil.toEnum(dto.getEstadoCivil()));
        doador.setGrupoSanquineo(GrupoSanguineo.toEnum(dto.getGrupoSanguineo()));
        doador.setCelular(dto.getCelular());
        doador.setPeso(dto.getPeso());

        if (doador.getEndereco() != null) {
            Optional<Endereco> objEndereco = enderecoRepository.findById(doador.getEndereco().getId());
            endereco = objEndereco.get();
        }

        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCep(dto.getEndereco().getCep());
        endereco.setLocalidade(dto.getEndereco().getLocalidade());
        endereco.setEstado(dto.getEndereco().getEstado());
        endereco.setDoador(doador);
        doador.setEndereco(endereco);
    }
}
