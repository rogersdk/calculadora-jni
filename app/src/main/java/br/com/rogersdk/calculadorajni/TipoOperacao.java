package br.com.rogersdk.calculadorajni;

import java.util.Arrays;

public enum TipoOperacao {
    SUM("+"), MINUS("-"), DIVIDE("÷"), MULTIPLY("x");

    String descricao;

    TipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoOperacao obterPorSimbolo(String simbolo) {
        return Arrays.asList(values()).stream().filter(value -> value.getDescricao().equals(simbolo)).findFirst().get();
    }

    public String getDescricao() {
        return descricao;
    }
}
