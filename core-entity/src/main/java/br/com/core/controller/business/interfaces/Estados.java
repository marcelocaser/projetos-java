package br.com.core.controller.business.interfaces;

import br.com.core.entity.EstadosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Estados {
     
    public void alterar(EstadosTO estadosTO);

    public void excluir(EstadosTO estadosTO);

    public void incluir(EstadosTO estadosTO);

    public EstadosTO consultar(EstadosTO estadosTO);

    public List<EstadosTO> listar(EstadosTO estadosTO);
    
}
