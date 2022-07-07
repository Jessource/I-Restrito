package com.serasa.erestrito.domain.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.engine.profile.Fetch;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serasa.erestrito.domain.entity.Produto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ComentarioProdutoDto {
    @NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50)
	private String descricao;

	@NotNull
	private Long produto;

    
}
