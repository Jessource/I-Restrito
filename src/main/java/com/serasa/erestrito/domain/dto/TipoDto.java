package com.serasa.erestrito.domain.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDto {
	
	@NotBlank
	@Length(min = 5, max = 50)
	@Column(length = 50)
	private String descricao;

}
