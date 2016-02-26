package br.com.core.persistence.interfaces;

import br.com.core.entity.ComplementosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Complementos {

    public void alterar(ComplementosTO complementosTO);

    public void excluir(ComplementosTO complementosTO);

    public void incluir(ComplementosTO complementosTO);

    public ComplementosTO consultar(ComplementosTO complementosTO);

    public List<ComplementosTO> listar(ComplementosTO complementosTO);

}
