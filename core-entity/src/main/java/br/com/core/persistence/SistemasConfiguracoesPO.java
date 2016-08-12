package br.com.core.persistence;

import br.com.core.entity.SistemasConfiguracoesTO;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class SistemasConfiguracoesPO extends Persistence<SistemasConfiguracoesTO> {

    public SistemasConfiguracoesPO() {
        setClazz(SistemasConfiguracoesTO.class);
    }

    @Transactional
    public void alterar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        update(sistemasConfiguracoesTO);
    }

    @Transactional
    public void excluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        delete(sistemasConfiguracoesTO);
    }

    @Transactional
    public void incluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        create(sistemasConfiguracoesTO);
    }

    public SistemasConfiguracoesTO consultar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return find(sistemasConfiguracoesTO);
    }

    public List<SistemasConfiguracoesTO> listar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return list(sistemasConfiguracoesTO);
    }

}
