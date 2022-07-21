package com.serasa.erestrito.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.enums.Restricao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
  @NotBlank
	@Length(min = 5, max = 50)
	private String nome;

	@NotBlank
	@Length(min = 5, max = 50)
	private String descricao;

  @NotNull
  @Enumerated(EnumType.STRING)
	private Restricao restricao;

  @NotNull
	private String marca;

  @NotNull
	private String origem;

  @NotNull
	private String adicao;

  @NotNull
	private String tipoProduto;

  public Produto converte() {
    Produto produto = new Produto();
    
    produto.setNome(this.getNome());
    produto.setDescricao(this.getDescricao());
    produto.setRestricao(this.getRestricao());
    produto.setMarca(this.getMarca());
    produto.setOrigem(this.getOrigem());
    produto.setAdicao(this.getAdicao());
    produto.setTipoProduto(this.getTipoProduto());

    return produto;
  }
}