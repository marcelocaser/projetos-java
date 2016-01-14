package br.com.core.enumerator;

/**
 *
 * @author marcelo
 */
public enum EnumStatusPedido {

    PENDENTE_EXPORTACAO("PENDENTE EXPORTAÇÃO"),
    BLOQUEIO_COMERCIAL("BLOQUEIO COMERCIAL");

    private final String nome;

    EnumStatusPedido(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
