package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.SistemasConfiguracoes;
import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.persistence.SistemasConfiguracoesPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class SistemasConfiguracoesBO implements SistemasConfiguracoes {

    @Autowired
    SistemasConfiguracoesPO persistencia;

    @Override
    public void alterar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.alterar(sistemasConfiguracoesTO);
    }

    @Override
    public void excluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.excluir(sistemasConfiguracoesTO);

    }

    @Override
    public void incluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.incluir(sistemasConfiguracoesTO);
    }

    @Override
    public SistemasConfiguracoesTO consultar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return this.persistencia.consultar(sistemasConfiguracoesTO);
    }

    @Override
    public List<SistemasConfiguracoesTO> listar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return this.persistencia.listar(sistemasConfiguracoesTO);
    }

}
