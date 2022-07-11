package com.serasa.erestrito.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private String nome;
	private String sobrenome;
	private String email;
	private String uf;
	private Date dataDeNascimento;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, key, nome, sobrenome, uf, dataDeNascimento);
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
		return Objects.equals(dataDeNascimento, other.dataDeNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(key, other.key) && Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome) && Objects.equals(uf, other.uf);
	}

}
