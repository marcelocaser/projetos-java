package br.com.qbe.ws.seguro.business.controller.persistence;

import br.com.core.persistence.Persistence;
import br.com.qbe.entity.CancelamentosTO;
import br.com.qbe.ws.seguro.business.controller.persistence.interfaces.Cancelamentos;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marce
 */
@Repository
public class CancelamentosPO extends Persistence<CancelamentosTO> implements Cancelamentos {

    public CancelamentosPO() {
        setClazz(CancelamentosTO.class);
    }

    @Override
    public void alterar(CancelamentosTO cancelamentosTO) {
        update(cancelamentosTO);
    }

    @Override
    public void excluir(CancelamentosTO cancelamentosTO) {
        delete(cancelamentosTO);
    }

    @Override
    public void incluir(CancelamentosTO cancelamentosTO) {
        create(cancelamentosTO);
    }

    @Override
    public CancelamentosTO consultar(CancelamentosTO cancelamentosTO) {
        return find(cancelamentosTO);
    }

    @Override
    public List<CancelamentosTO> listar(CancelamentosTO cancelamentosTO) {
        return list(cancelamentosTO);
    }
    
}
