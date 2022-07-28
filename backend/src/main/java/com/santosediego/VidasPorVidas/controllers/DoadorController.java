package com.santosediego.VidasPorVidas.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santosediego.VidasPorVidas.dto.DoadorDTO;
import com.santosediego.VidasPorVidas.dto.DoadorExportDTO;
import com.santosediego.VidasPorVidas.services.DoadorService;

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

	@GetMapping(value = "/export")
	public ResponseEntity<List<DoadorExportDTO>> exportAll() {
		List<DoadorExportDTO> listDTO = service.exportAll();
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

	@GetMapping("/download")
	public ResponseEntity<?> download() {

		Resource resource = service.provideFileCSV();

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resource);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) {

		List<DoadorDTO> listDTO = service.uploadFile(multipartFile);
		return ResponseEntity.ok().body(listDTO);
	}
}