package br.com.core.exception;

import br.com.core.message.Mensagem;
import br.com.core.message.MensagemLista;
import java.util.ArrayList;

/**
 *
 * @author marcelocaser
 */
public class NegocioException extends Exception {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();
    private int indice = 0;

    public NegocioException() {
        super();
    }

    /**
     * Construtor que adiciona apenas uma mensagem
     *
     * @param mensagem - String
     */
    public NegocioException(String mensagem) {
        super();
        setMensagem(mensagem);
    }

    /**
     * Construtor que adiciona apenas uma mensagem
     *
     * @param mensagem - Mensagem
     */
    public NegocioException(Mensagem mensagem) {
        super();
        setMensagem(mensagem);
    }

    /**
     * Construtor que adiciona uma lista de mensagens
     *
     * @param mensagemLista - MensagemLista
     */
    public NegocioException(MensagemLista mensagemLista) {
        super();
        setMensagens(mensagemLista);
    }

    // *** Gets and Sets ***
    public void setMensagem(String mensagem) {
        this.mensagens.add(new Mensagem(mensagem));
    }

    public void setMensagem(Mensagem mensagemTO) {
        this.mensagens.add(mensagemTO);
    }

    public void setMensagens(MensagemLista mensagemListaTO) {
        this.mensagens = mensagemListaTO.getMensagens();
    }

    public void proximaMensagem() {
        if (indice < this.mensagens.size()) {
            indice = indice + 1;
        }
    }

    public String getMensagem() {
        if (mensagens.size() >= indice) {
            return this.mensagens.get(indice).toString();
        }
        return null;
    }

    public ArrayList<Mensagem> getMensagens() {
        if (mensagens.size() >= 0) {
            return this.mensagens;
        }
        return null;
    }

    public int getNumeroMensagens() {
        return mensagens.size();
    }

}
