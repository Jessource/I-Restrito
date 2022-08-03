package com.serasa.erestrito.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serasa.erestrito.domain.enums.Restricao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50, nullable = false)
	private String nome;

	@NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50, nullable = false)
	private String descricao;

	@Column(nullable = true)
	private String foto;

	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Restricao restricao;

	@Column(nullable = true)
	private String origem;

	@Column(nullable = true)
	private String marca;

	@Column(nullable = true)
	private String adicao;

	@Column(nullable = true)
	private String tipoProduto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonIgnore
	private Usuario usuario;

	public Produto(String nome, String descricao, String foto, Restricao restricao, String origem,
			String marca, String adicao, String tipoProduto, Usuario usuario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.foto = foto;
		this.restricao = restricao;
		this.origem = origem;
		this.marca = marca;
		this.adicao = adicao;
		this.tipoProduto = tipoProduto;
		this.usuario = usuario;
	}
	
	
	
}
