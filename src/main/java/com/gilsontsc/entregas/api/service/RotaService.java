package com.gilsontsc.entregas.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilsontsc.entregas.api.dto.CoordenadaDto;
import com.gilsontsc.entregas.api.dto.RotaDto;
import com.gilsontsc.entregas.api.entity.CoordenadaEntity;
import com.gilsontsc.entregas.api.entity.RotaEntity;
import com.gilsontsc.entregas.api.repository.RotaRepository;

@Service
public class RotaService {

	@Autowired
	private RotaRepository repository;
	
	@Autowired
	private CoordenadaService coordenadaService;
	
	@Transactional
	public RotaEntity salvar(RotaEntity rota) {
		return this.repository.save(rota);
	}
	
	public Optional<RotaEntity> buscarPorId(Long id){
		return this.repository.findById(id);
	}
	
	public Optional<RotaEntity> buscarPorNome(String nome){
		return this.repository.findByNome(nome);
	}
	
	public Optional<RotaEntity> buscarPorVeiculo(Long id){
		return this.repository.findByVeiculoId(id);
	}
	
	public Optional<List<RotaEntity>> buscarPorStatus(String status){
		return this.repository.findByStatus(status);
	}
	
	public List<RotaEntity> buscarTodos(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void deletar(Long id){
		this.repository.deleteById(id);
	}
	
	public RotaEntity converteDtoParaEntity(RotaDto dto) {
		List<CoordenadaEntity> coordList = new ArrayList<>();
		dto.getParadas().stream().forEach(coord -> {
			coordList.add(this.coordenadaService.converteDtoParaEntity(coord));
		});
		return RotaEntity.builder()
				.nome(dto.getNome())
				.status(dto.getStatus())
				.veiculoId(dto.getVeiculoId())
				.instante(dto.getInstante())
				.paradas(coordList)
				.build();
	}
	
	public RotaDto converteEntityParaDto(RotaEntity entity) {
		List<CoordenadaDto> coordList = new ArrayList<>();
		entity.getParadas().stream().forEach(coord -> {
			coordList.add(this.coordenadaService.converteEntityParaDto(coord));
		});
		return RotaDto.builder()
				.nome(entity.getNome())
				.status(entity.getStatus())
				.veiculoId(entity.getVeiculoId())
				.instante(entity.getInstante())
				.paradas(coordList)
				.build();
	}
	
}