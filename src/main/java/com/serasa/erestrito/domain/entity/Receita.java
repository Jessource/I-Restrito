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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serasa.erestrito.domain.enums.Restricao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receitas")
@NoArgsConstructor
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_receita")
	private Long id;

	@Column(length = 50)
	private String titulo;

	@Column(nullable = false)
	private String ingredientes;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String modoDeFazer;

	@Column(nullable = false)
	private String tempoDePreparo;

	@Column(nullable = false)
	private String rendimento;

	@Column(nullable = true)
	private String foto;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Restricao restricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonIgnore
	private Usuario usuario;

	public Receita(String titulo, String ingredientes, String modoDeFazer, String tempoDePreparo, String rendimento,
			String foto, Restricao restricao, Usuario usuario) {
		super();
		this.titulo = titulo;
		this.ingredientes = ingredientes;
		this.modoDeFazer = modoDeFazer;
		this.tempoDePreparo = tempoDePreparo;
		this.rendimento = rendimento;
		this.foto = foto;
		this.restricao = restricao;
		this.usuario = usuario;
	}
}
