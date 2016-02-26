package br.com.core.controller.business;

import br.com.core.entity.ComplementosTO;
import br.com.core.persistence.interfaces.Complementos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class ComplementosBO {
    
     @Autowired
     Complementos persistencia;

    public void alterar(ComplementosTO complementosTO) {
        this.persistencia.alterar(complementosTO);
    }
    
    private void antesDeAlterar(ComplementosTO complementosTO) {
        complementosTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(ComplementosTO complementosTO) {
        complementosTO.setExclusao(new Date());
    }

    public void excluir(ComplementosTO ComplementosTO) {
        antesDeExcluir(ComplementosTO);
        this.persistencia.excluir(ComplementosTO);

    }

    public void incluir(ComplementosTO ComplementosTO) {
        antesDeAlterar(ComplementosTO);
        this.persistencia.incluir(ComplementosTO);
    }

    public ComplementosTO consultar(ComplementosTO ComplementosTO) {
        return this.persistencia.consultar(ComplementosTO);
    }

    public List<ComplementosTO> listar(ComplementosTO ComplementosTO) {
        return this.persistencia.listar(ComplementosTO);
    }
    
}
