package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Estados;
import br.com.core.entity.EstadosTO;
import br.com.core.persistence.EstadosPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class EstadosBO implements Estados {
    
    @Autowired
    EstadosPO persistencia;

    @Override
    public void alterar(EstadosTO estadosTO) {
        this.persistencia.alterar(estadosTO);
    }

    @Override
    public void excluir(EstadosTO estadosTO) {
        this.persistencia.excluir(estadosTO);

    }

    @Override
    public void incluir(EstadosTO estadosTO) {
        this.persistencia.incluir(estadosTO);
    }

    @Override
    public EstadosTO consultar(EstadosTO estadosTO) {
        return this.persistencia.consultar(estadosTO);
    }

    @Override
    public List<EstadosTO> listar(EstadosTO estadosTO) {
        return this.persistencia.listar(estadosTO);
    }

}
