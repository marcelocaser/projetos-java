package br.com.core.message;

import java.io.Serializable;

/**
 *
 * @author marcelocaser
 */
public class Mensagem implements Serializable {

    private String mensagem = new String();

    public Mensagem() {
    }

    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
