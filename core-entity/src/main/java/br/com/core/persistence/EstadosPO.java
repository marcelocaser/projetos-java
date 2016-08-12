package br.com.core.persistence;

import br.com.core.entity.EstadosTO;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class EstadosPO extends Persistence<EstadosTO> {

    public EstadosPO() {
        setClazz(EstadosTO.class);
    }

    @Transactional
    public void alterar(EstadosTO estadosTO) {
        update(estadosTO);
    }

    @Transactional
    public void excluir(EstadosTO estadosTO) {
        delete(estadosTO);
    }

    @Transactional
    public void incluir(EstadosTO estadosTO) {
        create(estadosTO);
    }

    public EstadosTO consultar(EstadosTO estadosTO) {
        return find(estadosTO);
    }

    public List<EstadosTO> listar(EstadosTO estadosTO) {
        return list(estadosTO);
    }

}
