package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.ServicosTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Servicos;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class ServicosPO extends Persistence<ServicosTO> implements Servicos {

    public ServicosPO() {
        setClazz(ServicosTO.class);
    }

    @Override
    @Transactional
    public void alterar(ServicosTO servicosTO) {
        update(servicosTO);
    }

    @Override
    @Transactional
    public void excluir(ServicosTO servicosTO) {
        delete(servicosTO);
    }

    @Override
    @Transactional
    public void incluir(ServicosTO servicosTO) {
        create(servicosTO);
    }

    @Override
    public ServicosTO consultar(ServicosTO servicosTO) {
        return find(servicosTO);
    }

    @Override
    public List<ServicosTO> listar(ServicosTO servicosTO) {
        return list(servicosTO);
    }

}
