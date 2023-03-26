package com.santosediego.VidasPorVidas.controllers;

import com.santosediego.VidasPorVidas.dto.DoadorDTO;
import com.santosediego.VidasPorVidas.services.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/doadores")
public class DoadorController {

	@Autowired
	private DoadorService service;

	@GetMapping
	public Page<DoadorDTO> findAll(
			@RequestParam(value = "conditions", defaultValue = "") String conditions,
			Pageable pegeable) {
		return service.findAll(conditions, pegeable);
	}

	@GetMapping(value = "/{id}")
	public DoadorDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<DoadorDTO>> list() {
		List<DoadorDTO> listDTO = service.listAll();
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<DoadorDTO> insert(@Valid @RequestBody DoadorDTO dto) {
		dto = service.insert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DoadorDTO> update(@PathVariable Long id, @Valid @RequestBody DoadorDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DoadorDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
