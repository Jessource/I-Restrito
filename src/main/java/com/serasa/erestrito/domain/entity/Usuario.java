package com.serasa.erestrito.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long id;
	
	@NotBlank
	@Size(max=20)
	@Column(name="nome_usuario")
	private String nome;
	
	@NotBlank
	@Size(max=40)
	@Column(name="sobrenome_usuario")
	private String sobrenome;
	
	@NotBlank
	@Size(max=80)
	@Email
	@Column(name="email_usuario")
	private String email;
	
	@NotBlank
	@Size(max=2)
	@Column(name="uf_usuario")
	private String uf;
	 
	@DateTimeFormat
	@Past
	@Column(name="dataNascimento_usuario")
	private Date dataDeNascimento;

}
