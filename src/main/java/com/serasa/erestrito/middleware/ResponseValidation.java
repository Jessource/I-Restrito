package com.serasa.erestrito.middleware;

public class ResponseValidation {
  private String campo;
	private String mensagem;
	
	public ResponseValidation(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}