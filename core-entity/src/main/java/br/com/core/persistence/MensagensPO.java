package br.com.core.persistence;

import br.com.core.entity.MensagensTO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marce
 */
@Repository
public class MensagensPO extends Persistence<MensagensTO> {

    public MensagensPO() {
        setClazz(MensagensTO.class);
    }

    public List<MensagensTO> listar() {
        return list(new MensagensTO());
    }

}
