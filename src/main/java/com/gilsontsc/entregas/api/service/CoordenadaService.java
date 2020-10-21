package com.gilsontsc.entregas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilsontsc.entregas.api.entity.CoordenadaEntity;
import com.gilsontsc.entregas.api.repository.CoordenadaRepository;

@Service
public class CoordenadaService {

	@Autowired
	private CoordenadaRepository repository;
	
	@Transactional
	public CoordenadaEntity salvar(CoordenadaEntity coordenada) {
		return this.repository.save(coordenada);
	}
	
	public Optional<CoordenadaEntity> buscarPorId(Long id){
		return this.repository.findById(id);
	}
	
	public List<CoordenadaEntity> buscarTodos(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void deletar(Long id){
		this.repository.deleteById(id);
	}
	
}