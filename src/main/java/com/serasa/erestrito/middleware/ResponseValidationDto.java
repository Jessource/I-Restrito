package com.serasa.erestrito.middleware;

public class ResponseValidationDto {
  private String campo;
	private String mensagem;
	
	public ResponseValidationDto(String campo, String mensagem) {
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