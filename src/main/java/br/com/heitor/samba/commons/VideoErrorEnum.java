package br.com.heitor.samba.commons;

import lombok.Getter;

@Getter
public enum VideoErrorEnum {
	INVALID_TIMESTAMP("ERRO TIMESTAMP");

	private String valor;

	VideoErrorEnum(String valor) {
		this.valor = valor;
	}
}
