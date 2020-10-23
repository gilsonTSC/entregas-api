package com.gilsontsc.entregas.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RotaDto {

private Long id;
	
	@NotNull(message = "Informe o nome da rota")
	private String nome;
	
	@NotNull(message = "Informe o status da rota")
	private String status;
	
	@NotNull(message = "Informe o id do veiculo")
	private Long veiculoId;
	
	@NotNull(message = "Informe a data")
	private Date instante;
	
	private List<CoordenadaDto> paradas;
	
}
