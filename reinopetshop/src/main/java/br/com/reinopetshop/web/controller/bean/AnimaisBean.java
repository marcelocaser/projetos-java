package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.AnimaisTO;
import br.com.reinopetshop.business.controller.ReinoPetController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class AnimaisBean extends ReinoPetController {

    private AnimaisTO animaisTO;

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
        return "";
    }

    public String salvar() {
        try {
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public AnimaisTO getAnimaisTO() {
        return animaisTO;
    }

    public void setAnimaisTO(AnimaisTO animaisTO) {
        this.animaisTO = animaisTO;
    }

}
