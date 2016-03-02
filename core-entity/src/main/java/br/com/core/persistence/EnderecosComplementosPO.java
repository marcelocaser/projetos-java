package br.com.core.persistence;

import br.com.core.entity.EnderecosComplementosTO;
import br.com.core.persistence.interfaces.EnderecosComplementos;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class EnderecosComplementosPO extends Persistence<EnderecosComplementosTO> implements EnderecosComplementos {

    public EnderecosComplementosPO() {
        setClazz(EnderecosComplementosTO.class);
    }

    @Override
    @Transactional
    public void alterar(EnderecosComplementosTO complementosTO) {
        update(complementosTO);
    }

    @Override
    @Transactional
    public void excluir(EnderecosComplementosTO complementosTO) {
        delete(complementosTO);
    }

    @Override
    @Transactional
    public void incluir(EnderecosComplementosTO complementosTO) {
        create(complementosTO);
    }

    @Override
    public EnderecosComplementosTO consultar(EnderecosComplementosTO complementosTO) {
        return find(complementosTO);
    }

    @Override
    public List<EnderecosComplementosTO> listar(EnderecosComplementosTO complementosTO) {
        return list(complementosTO);
    }
    
}
