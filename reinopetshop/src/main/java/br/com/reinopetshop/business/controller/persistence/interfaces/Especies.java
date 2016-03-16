package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.EspeciesTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Especies {
    
     public void alterar(EspeciesTO especiesTO);

    public void excluir(EspeciesTO especiesTO);

    public void incluir(EspeciesTO especiesTO);

    public EspeciesTO consultar(EspeciesTO especiesTO);

    public List<EspeciesTO> listar(EspeciesTO especiesTO);
    
}
