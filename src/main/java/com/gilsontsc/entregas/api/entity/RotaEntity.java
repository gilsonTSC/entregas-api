package com.gilsontsc.entregas.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_rota")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RotaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "cd_rota_id")
	private Long id;
	
	@Column(name = "ds_nome_rota", nullable = false)
	private String nome;
	
	@Column(name = "ds_status_rota", nullable = false)
	private String status;
	
	@Column(name = "cd_veiculo_id", nullable = false)
	private Long veiculoId;
	
	@Column(name = "dt_instante")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@JsonIgnore
	@OneToMany(mappedBy="id")
	private List<CoordenadaEntity> paradas;
	
}
