package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.ServicosCategoriasTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.ServicosCategorias;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class ServicosCategoriasBO {

    @Autowired
    ServicosCategorias persistencia;

    public void alterar(ServicosCategoriasTO servicosCategoriasTO) {
        antesDeAlterar(servicosCategoriasTO);
        this.persistencia.alterar(servicosCategoriasTO);
    }

    private void antesDeAlterar(ServicosCategoriasTO servicosCategoriasTO) {
        servicosCategoriasTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(ServicosCategoriasTO servicosCategoriasTO) {
        servicosCategoriasTO.setExclusao(new Date());
    }

    public void excluir(ServicosCategoriasTO servicosCategoriasTO) {
        antesDeExcluir(servicosCategoriasTO);
        this.persistencia.excluir(servicosCategoriasTO);

    }

    public void incluir(ServicosCategoriasTO servicosCategoriasTO) {
        antesDeAlterar(servicosCategoriasTO);
        this.persistencia.incluir(servicosCategoriasTO);
    }

    public ServicosCategoriasTO consultar(ServicosCategoriasTO servicosCategoriasTO) {
        return this.persistencia.consultar(servicosCategoriasTO);
    }

    public List<ServicosCategoriasTO> listar(ServicosCategoriasTO servicosCategoriasTO) {
        return this.persistencia.listar(servicosCategoriasTO);
    }

}
