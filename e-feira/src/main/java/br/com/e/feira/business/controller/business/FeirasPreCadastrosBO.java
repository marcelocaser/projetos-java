package br.com.e.feira.business.controller.business;

import br.com.core.entity.FeirasPreCadastrosTO;
import br.com.e.feira.business.controller.persistence.interfaces.FeirasPreCadastros;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marce
 */
@Named
public class FeirasPreCadastrosBO {
    
    @Inject
    FeirasPreCadastros persistencia;

    public void alterar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        this.persistencia.alterar(feirasPreCadastrosTO);
    }

    public void excluir(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        this.persistencia.excluir(feirasPreCadastrosTO);
    }

    public void incluir(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        this.persistencia.incluir(feirasPreCadastrosTO);
    }

    public FeirasPreCadastrosTO consultar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        return this.persistencia.consultar(feirasPreCadastrosTO);
    }

    public List<FeirasPreCadastrosTO> listar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        return this.persistencia.listar(feirasPreCadastrosTO);
    }
}
