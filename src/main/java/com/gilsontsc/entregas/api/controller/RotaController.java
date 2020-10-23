package com.gilsontsc.entregas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("{id}")
	public ResponseEntity<Object> buscar(@PathVariable("id") Long id) {
		return rotaService.buscarPorId(id)
				.map(rota -> new ResponseEntity<Object>(this.rotaService.converteEntityParaDto(rota), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<Object>("Rota não encontrada pelo id: " + id, HttpStatus.NOT_FOUND));
	}

}