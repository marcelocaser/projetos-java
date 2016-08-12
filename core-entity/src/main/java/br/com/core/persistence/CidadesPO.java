package br.com.core.persistence;

import br.com.core.entity.CidadesTO;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Repository
public class CidadesPO extends Persistence<CidadesTO> {

    public CidadesPO() {
        setClazz(CidadesTO.class);
    }

    @Transactional
    public void alterar(CidadesTO cidadesTO) {
        update(cidadesTO);
    }

    @Transactional
    public void excluir(CidadesTO cidadesTO) {
        delete(cidadesTO);
    }

    @Transactional
    public void incluir(CidadesTO cidadesTO) {
        create(cidadesTO);
    }

    public CidadesTO consultar(CidadesTO cidadesTO) {
        return find(cidadesTO);
    }

    public List<CidadesTO> listar(CidadesTO cidadesTO) {
        return list(cidadesTO);
    }

}
