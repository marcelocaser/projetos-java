package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.RacasTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Racas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class RacasBO {

    @Autowired
    Racas persistencia;

    public void alterar(RacasTO racasTO) {
        this.persistencia.alterar(racasTO);
    }

    public void excluir(RacasTO racasTO) {
        this.persistencia.excluir(racasTO);

    }

    public void incluir(RacasTO racasTO) {
        this.persistencia.incluir(racasTO);
    }

    public RacasTO consultar(RacasTO racasTO) {
        return this.persistencia.consultar(racasTO);
    }

    public List<RacasTO> listar(RacasTO racasTO) {
        return this.persistencia.listar(racasTO);
    }

}
