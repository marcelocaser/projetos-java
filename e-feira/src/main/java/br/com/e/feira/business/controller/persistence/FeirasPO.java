package br.com.e.feira.business.controller.persistence;

import br.com.core.persistence.Persistence;
import br.com.core.entity.FeirasTO;
import br.com.e.feira.business.controller.persistence.interfaces.Feiras;
import java.util.List;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Named
public class FeirasPO extends Persistence<FeirasTO> implements Feiras {

    public FeirasPO() {
        setClazz(FeirasTO.class);
    }

    @Override
    @Transactional
    public void alterar(FeirasTO feirasTO) {
        update(feirasTO);
    }

    @Override
    @Transactional
    public void excluir(FeirasTO feirasTO) {
        delete(feirasTO);
    }

    @Override
    @Transactional
    public void incluir(FeirasTO feirasTO) {
        create(feirasTO);
    }

    @Override
    public FeirasTO consultar(FeirasTO feirasTO) {
        return find(feirasTO);
    }

    @Override
    public List<FeirasTO> listar(FeirasTO feirasTO) {
        return list(feirasTO);
    }

}
