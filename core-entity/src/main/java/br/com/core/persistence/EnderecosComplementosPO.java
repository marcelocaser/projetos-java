package br.com.core.persistence;

import br.com.core.entity.EnderecosComplementosTO;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class EnderecosComplementosPO extends Persistence<EnderecosComplementosTO> {

    public EnderecosComplementosPO() {
        setClazz(EnderecosComplementosTO.class);
    }

    @Transactional
    public void alterar(EnderecosComplementosTO complementosTO) {
        update(complementosTO);
    }

    @Transactional
    public void excluir(EnderecosComplementosTO complementosTO) {
        delete(complementosTO);
    }

    @Transactional
    public void incluir(EnderecosComplementosTO complementosTO) {
        create(complementosTO);
    }

    public EnderecosComplementosTO consultar(EnderecosComplementosTO complementosTO) {
        return find(complementosTO);
    }

    public List<EnderecosComplementosTO> listar(EnderecosComplementosTO complementosTO) {
        return list(complementosTO);
    }

}
