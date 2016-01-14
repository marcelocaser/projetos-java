package br.com.core.message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author marcelocaser
 */
public final class MensagemLista implements Serializable {

    private ArrayList<Mensagem> mensagens;

    /**
     * Construtor padrão
     */
    public MensagemLista() {
        mensagens = new ArrayList<Mensagem>();
    }

    /**
     * Constroi objeto e adiciona mensagem na lista
     *
     * @param mensagem - String
     */
    public MensagemLista(String mensagem) {
        mensagens = new ArrayList<Mensagem>();
        addMensagem(mensagem);
    }

    /**
     * Adiciona uma mensagem na lista
     *
     * @param mensagem - String
     */
    public void addMensagem(String mensagem) {
        this.mensagens.add(new Mensagem(mensagem));
    }

    // *** Gets and Sets ***
    public ArrayList<Mensagem> getMensagens() {
        return this.mensagens;
    }

    public void removeTodosElementos() {
        this.mensagens.clear();
    }

    public int getNumeroRegistros() {
        return this.mensagens.size();
    }

    public boolean isEmpty() {
        return this.mensagens.isEmpty();
    }

}
