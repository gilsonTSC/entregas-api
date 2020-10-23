package com.gilsontsc.entregas.api.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadaDto {

	@NotNull(message = "Informe a latitude")
	private Long latitude;
	
	@NotNull(message = "Informe a longitude")
	private Long longitude;
	
	@NotNull(message = "Informe a data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date instante;
	
	@NotNull(message = "Informe o id do veiculo")
	private Long veiculoId;
	
}