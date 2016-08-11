package br.com.core.persistence;

import br.com.core.entity.CidadesTO;
import br.com.core.persistence.interfaces.Cidades;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Repository
public class CidadesPO extends Persistence<CidadesTO> implements Cidades {

    public CidadesPO() {
        setClazz(CidadesTO.class);
    }

    @Override
    @Transactional
    public void alterar(CidadesTO cidadesTO) {
        update(cidadesTO);
    }

    @Override
    @Transactional
    public void excluir(CidadesTO cidadesTO) {
        delete(cidadesTO);
    }

    @Override
    @Transactional
    public void incluir(CidadesTO cidadesTO) {
        create(cidadesTO);
    }

    @Override
    public CidadesTO consultar(CidadesTO cidadesTO) {
        return find(cidadesTO);
    }

    @Override
    public List<CidadesTO> listar(CidadesTO cidadesTO) {
        return list(cidadesTO);
    }

}
