package com.serasa.erestrito.domain.dto;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.enums.Restricao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarProdutoDto {
	private Long id;

	private String nome;

	private String foto;

	private String descricao;

	private Restricao restricao;

	private String marca;

	private String origem;

	private String adicao;

	private String tipoProduto;

	private Long cadastradoPor;

	public ListarProdutoDto(Produto produto) {
		this.setId(produto.getId());
		this.setNome(produto.getNome());
		this.setFoto(produto.getFoto());
		this.setDescricao(produto.getDescricao());
		this.setRestricao(produto.getRestricao());
		this.setMarca(produto.getMarca());
		this.setOrigem(produto.getOrigem());
		this.setAdicao(produto.getAdicao());
		this.setTipoProduto(produto.getTipoProduto());
		this.setCadastradoPor(produto.getUsuario().getId());
	}
}
