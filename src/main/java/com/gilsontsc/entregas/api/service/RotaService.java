package com.gilsontsc.entregas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilsontsc.entregas.api.entity.RotaEntity;
import com.gilsontsc.entregas.api.repository.RotaRepository;

@Service
public class RotaService {

	@Autowired
	private RotaRepository repository;
	
	@Transactional
	public RotaEntity salvar(RotaEntity rota) {
		return this.repository.save(rota);
	}
	
	public Optional<RotaEntity> buscarPorId(Long id){
		return this.repository.findById(id);
	}
	
	public List<RotaEntity> buscarTodos(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void deletar(Long id){
		this.repository.deleteById(id);
	}
	
}