package com.devsuperior.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	@Autowired
	private CityService service;

	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
		 dto = service.insert(dto);
		 //colocar o cabeario da resposta de inserção
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		 //retorno com http 201(criado)
		 return ResponseEntity.created(uri).body(dto);
		 
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CityDTO> delete(@PathVariable Long id){
		  service.delete(id);
		  return ResponseEntity.noContent().build();
		  
	}
	
}
