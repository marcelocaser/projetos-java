package br.com.core.controller.business;

import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.persistence.interfaces.SistemasConfiguracoes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class SistemasConfiguracoesBO {

    @Autowired
    SistemasConfiguracoes persistencia;

    public void alterar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.alterar(sistemasConfiguracoesTO);
    }

    public void excluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.excluir(sistemasConfiguracoesTO);

    }

    public void incluir(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.persistencia.incluir(sistemasConfiguracoesTO);
    }

    public SistemasConfiguracoesTO consultar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return this.persistencia.consultar(sistemasConfiguracoesTO);
    }

    public List<SistemasConfiguracoesTO> listar(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        return this.persistencia.listar(sistemasConfiguracoesTO);
    }

}
