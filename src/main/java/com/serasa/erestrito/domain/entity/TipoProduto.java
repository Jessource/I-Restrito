package com.serasa.erestrito.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tipo_produto")
public class TipoProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Length(min = 3, max = 50)
	@Column(length = 50)
	private String descricao;
		
	public TipoProduto(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public TipoProduto(Long id) {
		this.id = id;
	}
	
	


	

}
