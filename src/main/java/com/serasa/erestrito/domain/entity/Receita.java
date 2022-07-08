package com.serasa.erestrito.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "receitas")
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_receita")
	private Long id;
	
	@Column(length = 50)
	private String titulo;
	
	@Column(length = 255)
	private String ingredientes;

	@Column(columnDefinition = "TEXT")
	private String modoDeFazer;

	@Column(length = 255)
	private String tempoDePreparo;

	@Column(length = 255)
	private String rendimento;
	
	@Column(nullable = true)
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="restricao_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoRestricao tipoRestricao;

		
}
