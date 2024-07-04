package com.devsuperior.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository repository;
	
	public List<CityDTO> findAll(){
	   	List<City> list = repository.findAll(Sort.by("name"));
	   	
	   	return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

}
