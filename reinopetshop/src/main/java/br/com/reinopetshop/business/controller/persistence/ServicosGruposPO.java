package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.ServicosGruposTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.ServicosGrupos;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class ServicosGruposPO extends Persistence<ServicosGruposTO> implements ServicosGrupos {

    public ServicosGruposPO() {
        setClazz(ServicosGruposTO.class);
    }

    @Override
    @Transactional
    public void alterar(ServicosGruposTO servicosGruposTO) {
        update(servicosGruposTO);
    }

    @Override
    @Transactional
    public void excluir(ServicosGruposTO servicosGruposTO) {
        delete(servicosGruposTO);
    }

    @Override
    @Transactional
    public void incluir(ServicosGruposTO servicosGruposTO) {
        create(servicosGruposTO);
    }

    @Override
    public ServicosGruposTO consultar(ServicosGruposTO servicosGruposTO) {
        return find(servicosGruposTO);
    }

    @Override
    public List<ServicosGruposTO> listar(ServicosGruposTO servicosGruposTO) {
        return list(servicosGruposTO);
    }

}
