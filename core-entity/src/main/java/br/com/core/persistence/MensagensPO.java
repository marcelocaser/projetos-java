package br.com.core.persistence;

import br.com.core.entity.MensagensTO;
import br.com.core.persistence.interfaces.Mensagens;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marce
 */
@Repository
public class MensagensPO extends Persistence<MensagensTO> implements Mensagens {

    public MensagensPO() {
        setClazz(MensagensTO.class);
    }

    @Override
    public List<MensagensTO> listar() {
        return list(new MensagensTO());
    }

}
