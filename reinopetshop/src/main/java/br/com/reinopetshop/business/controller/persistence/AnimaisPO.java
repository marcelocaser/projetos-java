package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.AnimaisTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Animais;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class AnimaisPO extends Persistence<AnimaisTO> implements Animais {

    public AnimaisPO() {
        setClazz(AnimaisTO.class);
    }

    @Override
    @Transactional
    public void alterar(AnimaisTO animaisTO) {
        update(animaisTO);
    }

    @Override
    @Transactional
    public void excluir(AnimaisTO animaisTO) {
        delete(animaisTO);
    }

    @Override
    @Transactional
    public void incluir(AnimaisTO animaisTO) {
        create(animaisTO);
    }

    @Override
    public AnimaisTO consultar(AnimaisTO animaisTO) {
        return find(animaisTO);
    }

    @Override
    public List<AnimaisTO> listar(AnimaisTO animaisTO) {
        return list(animaisTO);
    }
    
}
