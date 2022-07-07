package com.serasa.erestrito.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.serasa.erestrito.domain.entity.Marca;
import com.serasa.erestrito.domain.entity.Origem;
import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.TipoAdicao;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.domain.entity.TipoRestricao;

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
	private Long tipoRestricao;

  @NotNull
	private Long marca;

  @NotNull
	private Long origem;

  @NotNull
	private Long tipoAdicao;

  @NotNull
	private Long tipoProduto;

  public Produto converte() {
    Produto produto = new Produto();
    
    produto.setNome(this.getNome());
    produto.setDescricao(this.getDescricao());
    produto.setTipoRestricao(new TipoRestricao(this.getTipoRestricao()));
    produto.setMarca(new Marca(this.getMarca()));
    produto.setOrigem(new Origem(this.getOrigem()));
    produto.setTipoAdicao(new TipoAdicao(this.getTipoAdicao()));
    produto.setTipoProduto(new TipoProduto(this.getTipoProduto()));

    return produto;
  }
}