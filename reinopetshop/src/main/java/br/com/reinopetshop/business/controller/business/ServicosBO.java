package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.ServicosTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Servicos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class ServicosBO {

    @Autowired
    Servicos persistencia;

    public void alterar(ServicosTO servicosTO) {
        antesDeAlterar(servicosTO);
        this.persistencia.alterar(servicosTO);
    }

    private void antesDeAlterar(ServicosTO servicosTO) {
        servicosTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(ServicosTO servicosTO) {
        servicosTO.setExclusao(new Date());
    }

    public void excluir(ServicosTO servicosTO) {
        antesDeExcluir(servicosTO);
        this.persistencia.excluir(servicosTO);

    }

    public void incluir(ServicosTO servicosTO) {
        antesDeAlterar(servicosTO);
        this.persistencia.incluir(servicosTO);
    }

    public ServicosTO consultar(ServicosTO servicosTO) {
        return this.persistencia.consultar(servicosTO);
    }

    public List<ServicosTO> listar(ServicosTO servicosTO) {
        return this.persistencia.listar(servicosTO);
    }
}
