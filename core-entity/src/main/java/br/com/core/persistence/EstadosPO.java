package br.com.core.persistence;

import br.com.core.entity.EstadosTO;
import br.com.core.persistence.interfaces.Estados;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class EstadosPO extends Persistence<EstadosTO> implements Estados {

    public EstadosPO() {
        setClazz(EstadosTO.class);
    }

    @Override
    @Transactional
    public void alterar(EstadosTO estadosTO) {
        update(estadosTO);
    }

    @Override
    @Transactional
    public void excluir(EstadosTO estadosTO) {
        delete(estadosTO);
    }

    @Override
    @Transactional
    public void incluir(EstadosTO estadosTO) {
        create(estadosTO);
    }

    @Override
    public EstadosTO consultar(EstadosTO estadosTO) {
        return find(estadosTO);
    }

    @Override
    public List<EstadosTO> listar(EstadosTO estadosTO) {
        return list(estadosTO);
    }

}
