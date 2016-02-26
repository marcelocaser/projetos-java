package br.com.core.persistence;

import br.com.core.entity.ComplementosTO;
import br.com.core.persistence.interfaces.Complementos;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class ComplementosPO extends Persistence<ComplementosTO> implements Complementos {

    public ComplementosPO() {
        setClazz(ComplementosTO.class);
    }

    @Override
    @Transactional
    public void alterar(ComplementosTO complementosTO) {
        update(complementosTO);
    }

    @Override
    @Transactional
    public void excluir(ComplementosTO complementosTO) {
        delete(complementosTO);
    }

    @Override
    @Transactional
    public void incluir(ComplementosTO complementosTO) {
        create(complementosTO);
    }

    @Override
    public ComplementosTO consultar(ComplementosTO complementosTO) {
        return find(complementosTO);
    }

    @Override
    public List<ComplementosTO> listar(ComplementosTO complementosTO) {
        return list(complementosTO);
    }
    
}
