package com.gilsontsc.entregas.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gilsontsc.entregas.api.entity.RotaEntity;

public interface RotaRepository extends JpaRepository<RotaEntity, Long> {

	Optional<RotaEntity> findByNome(String nome);
	
	Optional<RotaEntity> findByVeiculoId(Long id);
	
	Optional<List<RotaEntity>> findByStatus(String status);
	
}