package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ControllerNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		
		try {
			Event event = repository.getReferenceById(id);
			copyDtoToEntity(dto, event);
			event = repository.save(event);
			return new EventDTO(event);
			
			
		} catch (EntityNotFoundException e) {
			
			throw new ControllerNotFoundException("Evento não encontrado: " + id);
		}
	}	
		
		
	//auxiliar para copiar dto para entidade
	private void copyDtoToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(new City(dto.getCityId(),null));
	}

}
