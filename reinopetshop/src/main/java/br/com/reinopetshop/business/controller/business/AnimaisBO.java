package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.AnimaisTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Animais;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class AnimaisBO {

    @Autowired
    Animais persistencia;

    public void alterar(AnimaisTO animaisTO) {
        antesDeAlterar(animaisTO);
        this.persistencia.alterar(animaisTO);
    }

    private void antesDeAlterar(AnimaisTO animaisTO) {
        animaisTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(AnimaisTO animaisTO) {
        animaisTO.setExclusao(new Date());
    }

    public void excluir(AnimaisTO animaisTO) {
        antesDeExcluir(animaisTO);
        this.persistencia.excluir(animaisTO);

    }

    public void incluir(AnimaisTO animaisTO) {
        antesDeAlterar(animaisTO);
        this.persistencia.incluir(animaisTO);
    }

    public AnimaisTO consultar(AnimaisTO animaisTO) {
        return this.persistencia.consultar(animaisTO);
    }

    public List<AnimaisTO> listar(AnimaisTO animaisTO) {
        return this.persistencia.listar(animaisTO);
    }
    
}
