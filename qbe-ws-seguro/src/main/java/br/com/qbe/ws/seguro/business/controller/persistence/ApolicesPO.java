package br.com.qbe.ws.seguro.business.controller.persistence;

import br.com.core.persistence.Persistence;
import br.com.qbe.entity.ApolicesTO;
import br.com.qbe.ws.seguro.business.controller.persistence.interfaces.Apolices;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marce
 */
@Repository
public class ApolicesPO extends Persistence<ApolicesTO> implements Apolices {

    public ApolicesPO() {
        setClazz(ApolicesTO.class);
    }

    @Override
    public void alterar(ApolicesTO apolicesTO) {
        update(apolicesTO);
    }

    @Override
    public void excluir(ApolicesTO apolicesTO) {
        delete(apolicesTO);
    }

    @Override
    public void incluir(ApolicesTO apolicesTO) {
        create(apolicesTO);
    }

    @Override
    public ApolicesTO consultar(ApolicesTO apolicesTO) {
        return find(apolicesTO);
    }

    @Override
    public List<ApolicesTO> listar(ApolicesTO apolicesTO) {
        return list(apolicesTO);
    }

}
