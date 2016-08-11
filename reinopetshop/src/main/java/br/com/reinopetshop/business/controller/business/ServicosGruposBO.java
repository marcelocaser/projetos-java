package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.ServicosGruposTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.ServicosGrupos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class ServicosGruposBO {

    @Autowired
    ServicosGrupos persistencia;

    public void alterar(ServicosGruposTO servicosGruposTO) {
        antesDeAlterar(servicosGruposTO);
        this.persistencia.alterar(servicosGruposTO);
    }

    private void antesDeAlterar(ServicosGruposTO servicosGruposTO) {
        servicosGruposTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(ServicosGruposTO servicosGruposTO) {
        servicosGruposTO.setExclusao(new Date());
    }

    public void excluir(ServicosGruposTO servicosGruposTO) {
        antesDeExcluir(servicosGruposTO);
        this.persistencia.excluir(servicosGruposTO);

    }

    public void incluir(ServicosGruposTO servicosGruposTO) {
        antesDeAlterar(servicosGruposTO);
        this.persistencia.incluir(servicosGruposTO);
    }

    public ServicosGruposTO consultar(ServicosGruposTO servicosGruposTO) {
        return this.persistencia.consultar(servicosGruposTO);
    }

    public List<ServicosGruposTO> listar(ServicosGruposTO servicosGruposTO) {
        return this.persistencia.listar(servicosGruposTO);
    }

}
