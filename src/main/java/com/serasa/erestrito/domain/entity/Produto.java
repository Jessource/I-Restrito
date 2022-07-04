package com.serasa.erestrito.domain.entity;

import java.io.Serializable;

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
@Table(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Long id;
	
	@NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50)
	private String nome;
	
	@NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50)
	private String descricao;
	
	@Column(nullable = true)
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="restricao_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoRestricao tipoRestricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="marca_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="origem_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Origem origem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="adicao_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoAdicao tipoAdicao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoproduto_id", nullable = false) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoProduto tipoProduto;	
}
