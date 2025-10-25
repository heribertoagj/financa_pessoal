package br.com.ttech.backend.common.enums;

public enum TipoFaturamento {

    VALOR ("1", "Valor"),
    HORA("2", "Hora");

    private final String id;
    private final String descricao;

    TipoFaturamento(String id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
}
