package br.com.core.controller.business;

import br.com.core.entity.EstadosTO;
import br.com.core.persistence.interfaces.Estados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class EstadosBO {
    
    @Autowired
    Estados persistencia;

    public void alterar(EstadosTO estadosTO) {
        this.persistencia.alterar(estadosTO);
    }

    public void excluir(EstadosTO estadosTO) {
        this.persistencia.excluir(estadosTO);

    }

    public void incluir(EstadosTO estadosTO) {
        this.persistencia.incluir(estadosTO);
    }

    public EstadosTO consultar(EstadosTO estadosTO) {
        return this.persistencia.consultar(estadosTO);
    }

    public List<EstadosTO> listar(EstadosTO estadosTO) {
        return this.persistencia.listar(estadosTO);
    }

}
