package com.devsuperior.dsmovie.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.services.MovieService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Api(tags = "Movie Controller", value = "MovieController", description = "Controller for Movie")
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping
	public Page<MovieDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public MovieDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ApiOperation(value = "Create a new movie")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Product inserted successfully"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	public ResponseEntity<MovieDTO> insert(@RequestBody MovieDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}