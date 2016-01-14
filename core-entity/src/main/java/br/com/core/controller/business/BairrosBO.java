package br.com.core.controller.business;

import br.com.core.entity.BairrosTO;
import br.com.core.persistence.interfaces.Bairros;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marcelocaser
 */
@Component
public class BairrosBO {
    
    @Autowired
    Bairros persistencia;

    public void alterar(BairrosTO bairrosTO) {
        this.persistencia.alterar(bairrosTO);
    }

    public void excluir(BairrosTO bairrosTO) {
        this.persistencia.excluir(bairrosTO);
    }

    public void incluir(BairrosTO bairrosTO) {
        this.persistencia.incluir(bairrosTO);
    }

    public BairrosTO consultar(BairrosTO bairrosTO) {
        return this.persistencia.consultar(bairrosTO);
    }

    public List<BairrosTO> listar(BairrosTO bairrosTO) {
        return this.persistencia.listar(bairrosTO);
    }

}
