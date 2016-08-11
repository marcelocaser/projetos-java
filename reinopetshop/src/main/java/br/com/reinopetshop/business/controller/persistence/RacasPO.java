package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.RacasTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Racas;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class RacasPO extends Persistence<RacasTO> implements Racas {

    public RacasPO() {
        setClazz(RacasTO.class);
    }

    @Override
    @Transactional
    public void alterar(RacasTO racasTO) {
        update(racasTO);
    }

    @Override
    @Transactional
    public void excluir(RacasTO racasTO) {
        delete(racasTO);
    }

    @Override
    @Transactional
    public void incluir(RacasTO racasTO) {
        create(racasTO);
    }

    @Override
    public RacasTO consultar(RacasTO racasTO) {
        return find(racasTO);
    }

    @Override
    public List<RacasTO> listar(RacasTO racasTO) {
        return list(racasTO);
    }

}
