package com.gilsontsc.entregas.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gilsontsc.entregas.api.dto.RotaDto;
import com.gilsontsc.entregas.api.entity.RotaEntity;
import com.gilsontsc.entregas.api.service.CoordenadaService;
import com.gilsontsc.entregas.api.service.RotaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
public class RotaController {

	@Autowired
	private RotaService rotaService;

	@Autowired
	private CoordenadaService coordenadaService;

	@SuppressWarnings("unchecked")
	@GetMapping
	public ResponseEntity<List<RotaDto>> buscar(@RequestParam(value = "rotaId", required = false) Long rotaId,
			@RequestParam(value = "veiculoId", required = false) Long veiculoId,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "status", required = false) String status) {

		RotaEntity rota = RotaEntity.builder()
				.id(rotaId)
				.veiculoId(veiculoId)
				.nome(nome)
				.status(status)
				.build();
		
		List<RotaDto> listRota = new ArrayList<>();
		this.rotaService.buscar(rota).stream().forEach(rotaEntity -> {
			listRota.add(this.rotaService.converteEntityParaDto(rotaEntity));
		});
		
		return (ResponseEntity<List<RotaDto>>) listRota;
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> buscar(@PathVariable("id") Long id) {
		return rotaService.buscarPorId(id)
				.map(rota -> new ResponseEntity<Object>(this.rotaService.converteEntityParaDto(rota), HttpStatus.OK))
				.orElseGet(
						() -> new ResponseEntity<Object>("Rota não encontrada pelo id: " + id, HttpStatus.NOT_FOUND));
	}

}