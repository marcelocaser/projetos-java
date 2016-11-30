package br.com.qbe.ws.seguro.business.controller.persistence.interfaces;

import br.com.qbe.entity.CancelamentosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Cancelamentos {
    
    public void alterar(CancelamentosTO cancelamentosTO);

    public void excluir(CancelamentosTO cancelamentosTO);

    public void incluir(CancelamentosTO cancelamentosTO);

    public CancelamentosTO consultar(CancelamentosTO cancelamentosTO);

    public List<CancelamentosTO> listar(CancelamentosTO cancelamentosTO);
    
}
