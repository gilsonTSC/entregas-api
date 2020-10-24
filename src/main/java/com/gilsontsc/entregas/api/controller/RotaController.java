package com.gilsontsc.entregas.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gilsontsc.entregas.api.dto.RotaDto;
import com.gilsontsc.entregas.api.entity.RotaEntity;
import com.gilsontsc.entregas.api.service.CoordenadaService;
import com.gilsontsc.entregas.api.service.RotaService;
import com.gilsontsc.entregas.api.util.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
public class RotaController {

	@Autowired
	private RotaService rotaService;

	@Autowired
	private CoordenadaService coordenadaService;

	@GetMapping
	public ResponseEntity<Response<List<RotaDto>>> buscarRotas(@RequestParam(value = "rotaId", required = false) Long rotaId,
			@RequestParam(value = "veiculoId", required = false) Long veiculoId,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "status", required = false) String status) {

		RotaEntity rota = RotaEntity.builder()
				.id(rotaId)
				.veiculoId(veiculoId)
				.nome(nome)
				.status(status)
				.build();
		
		Response<List<RotaDto>> response = new Response<List<RotaDto>>();
		List<RotaDto> listRota = new ArrayList<>();
		this.rotaService.buscar(rota).stream().forEach(rotaEntity -> {
			listRota.add(this.rotaService.converteEntityParaDto(rotaEntity));
		});
		response.setData(listRota);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<RotaDto>> buscarRotaPorId(@PathVariable("id") Long id) {
		Response<RotaDto> response = new Response<RotaDto>();
		
		Optional<RotaEntity> rotaEntity = rotaService.buscarPorId(id);
		
		if(rotaEntity.isPresent()) {
			response.setData(this.rotaService.converteEntityParaDto(rotaEntity.get()));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		response.getErrors().add("Rota não encontrada pelo id: " + id);
		return ResponseEntity.badRequest().body(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<RotaDto>> salvarRota(@Valid @RequestBody RotaDto dto, BindingResult result){
		Response<RotaDto> response = new Response<RotaDto>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		RotaEntity rota = this.rotaService.salvar(this.rotaService.converteDtoParaEntity(dto));
		response.setData(this.rotaService.converteEntityParaDto(rota));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<RotaDto>> atualizar(@Valid @RequestBody RotaDto dto, BindingResult result) {
		Response<RotaDto> response = new Response<RotaDto>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<RotaEntity> rotaEntity = this.rotaService.buscarPorId(dto.getId());

		if(rotaEntity.isPresent()) {
			RotaEntity rota = this.rotaService.salvar(this.rotaService.converteDtoParaEntity(dto));
			response.setData(this.rotaService.converteEntityParaDto(rota));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		response.getErrors().add("Rota não encontrada");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<Response<String>> deleta(@Valid @RequestBody RotaDto dto, BindingResult result) {
		return deletaPorId(dto.getId(), result);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletaPorId(@PathVariable("id") Long id, BindingResult result) {
		Response<String> response = new Response<String>();
		
		Optional<RotaEntity> rotaEntity = this.rotaService.buscarPorId(id);

		if(rotaEntity.isPresent()) {
			this.rotaService.deletar(id);
			response.setData("Rota de nome "+ rotaEntity.get().getNome() + " apagada com sucesso!");
			return ResponseEntity.ok().body(response);
		}
		response.getErrors().add("Rota não encontrada");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}