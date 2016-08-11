package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.EspeciesTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Especies;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marce
 */
@Repository
public class EspeciesPO extends Persistence<EspeciesTO> implements Especies {

    public EspeciesPO() {
        setClazz(EspeciesTO.class);
    }

    @Override
    public void alterar(EspeciesTO especiesTO) {
        update(especiesTO);
    }

    @Override
    public void excluir(EspeciesTO especiesTO) {
        delete(especiesTO);
    }

    @Override
    public void incluir(EspeciesTO especiesTO) {
        create(especiesTO);
    }

    @Override
    public EspeciesTO consultar(EspeciesTO especiesTO) {
        return find(especiesTO);
    }

    @Override
    public List<EspeciesTO> listar(EspeciesTO especiesTO) {
        return list(especiesTO);
    }

}
