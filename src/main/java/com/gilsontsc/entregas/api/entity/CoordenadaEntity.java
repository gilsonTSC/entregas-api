package com.gilsontsc.entregas.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_coordenada")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "cd_coordenada_id")
	private Long id;
	
	@Column(name = "vl_latitude", nullable = false)
	private Long latitude;
	
	@Column(name = "vl_longitude", nullable = false)
	private Long longitude;
	
	@Column(name = "ds_descricao_coordenada")
	private String descricao;
	
	@Column(name = "vl_raio_entrega", nullable = false)
	private int raioDeEntrega;
	
	@Column(name = "dt_instante", nullable = false)
	private Date instante;
	
	@Column(name = "cd_veiculo_id", nullable = false)
	private Long veiculoId;
	
}