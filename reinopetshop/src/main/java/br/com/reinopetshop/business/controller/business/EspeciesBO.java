package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.EspeciesTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Especies;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class EspeciesBO {

    @Autowired
    Especies persistencia;

    public void alterar(EspeciesTO especiesTO) {
        this.persistencia.alterar(especiesTO);
    }

    public void excluir(EspeciesTO especiesTO) {
        this.persistencia.excluir(especiesTO);

    }

    public void incluir(EspeciesTO especiesTO) {
        this.persistencia.incluir(especiesTO);
    }

    public EspeciesTO consultar(EspeciesTO especiesTO) {
        return this.persistencia.consultar(especiesTO);
    }

    public List<EspeciesTO> listar(EspeciesTO especiesTO) {
        return this.persistencia.listar(especiesTO);
    }

}
