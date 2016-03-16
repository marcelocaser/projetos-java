package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.PessoasTO;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.PessoasBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class PessoasBean extends ReinoPetController {

    @Autowired
    private PessoasBO pessoasNegocio;
    private PessoasTO pessoasTO;

    /* Métodos para tratamento do negócio. */
    public String consultar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String editar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String excluir() {
        try {
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String novo() {
        pessoasTO = new PessoasTO();
        return "";
    }

    public String salvar() {
        try {
            this.pessoasNegocio.incluir(pessoasTO);
            return null;
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public PessoasBO getPessoasNegocio() {
        return pessoasNegocio;
    }

    public void setPessoasNegocio(PessoasBO pessoasNegocio) {
        this.pessoasNegocio = pessoasNegocio;
    }

    public PessoasTO getPessoasTO() {
        return pessoasTO;
    }

    public void setPessoasTO(PessoasTO pessoasTO) {
        this.pessoasTO = pessoasTO;
    }

}
