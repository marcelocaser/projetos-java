package br.com.e.feira.business.controller.business;

import br.com.core.entity.FeirasTO;
import br.com.e.feira.business.controller.persistence.interfaces.Feiras;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marce
 */
@Named
public class FeirasBO {

    @Inject
    Feiras persistencia;

    public void alterar(FeirasTO feirasTO) {
        this.persistencia.alterar(feirasTO);
    }

    public void excluir(FeirasTO feirasTO) {
        this.persistencia.excluir(feirasTO);
    }

    public void incluir(FeirasTO feirasTO) {
        this.persistencia.incluir(feirasTO);
    }

    public FeirasTO consultar(FeirasTO feirasTO) {
        return this.persistencia.consultar(feirasTO);
    }

    public List<FeirasTO> listar(FeirasTO feirasTO) {
        return this.persistencia.listar(feirasTO);
    }

}
