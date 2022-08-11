package com.serasa.erestrito.domain.vo.v1;

import java.io.Serializable;
import java.time.LocalDate;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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
	@Length(min = 2, max = 2)
	private String uf;
	@NotNull
	@Length(min = 2, max = 50)
	private String cidade;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	@NotNull
	private LocalDate dataNascimento;
	
	public UsuarioVO(String nome, String sobrenome, String email, String senha, String uf,String cidade, LocalDate dataNascimento, Perfil perfil) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.uf = uf;
		this.cidade = cidade;
		this.perfil = perfil;
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, key, nome, sobrenome, uf,cidade, dataNascimento);
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
				&& Objects.equals(sobrenome, other.sobrenome) && Objects.equals(uf, other.uf) && Objects.equals(cidade, other.cidade);
	}


	
	

}
