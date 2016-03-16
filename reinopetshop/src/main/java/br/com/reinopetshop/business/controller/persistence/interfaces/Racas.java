package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.RacasTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Racas {

    public void alterar(RacasTO racasTO);

    public void excluir(RacasTO racasTO);

    public void incluir(RacasTO racasTO);

    public RacasTO consultar(RacasTO racasTO);

    public List<RacasTO> listar(RacasTO racasTO);

}
