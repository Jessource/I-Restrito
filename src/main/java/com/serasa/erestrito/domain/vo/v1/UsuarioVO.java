package com.serasa.erestrito.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import com.serasa.erestrito.domain.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	
	@NotBlank
	@Length(min = 3)
	private String nome;

	@NotBlank
	@Length(min = 3)
	private String sobrenome;

	@NotBlank
	@Length(min = 6)
	@Email
	private String email;

	@NotBlank
	@Length(min = 4)
  private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	@NotNull
	private Date dataNascimento;

	@Override
	public int hashCode() {
		return Objects.hash(email, key, nome, sobrenome, dataNascimento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(key, other.key) && Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

}
