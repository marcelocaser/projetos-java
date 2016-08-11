package br.com.core.persistence;

import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.persistence.interfaces.SistemasConfiguracoes;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class SistemasConfiguracoesPO extends Persistence<SistemasConfiguracoesTO> implements SistemasConfiguracoes {

    public SistemasConfiguracoesPO() {
        setClazz(SistemasConfiguracoesTO.class);
    }

    @Override
    @Transactional
    public void alterar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        update(sistemasConfiguracoesTO);
    }

    @Override
    @Transactional
    public void excluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        delete(sistemasConfiguracoesTO);
    }

    @Override
    @Transactional
    public void incluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        create(sistemasConfiguracoesTO);
    }

    @Override
    public SistemasConfiguracoesTO consultar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return find(sistemasConfiguracoesTO);
    }

    @Override
    public List<SistemasConfiguracoesTO> listar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return list(sistemasConfiguracoesTO);
    }

}
