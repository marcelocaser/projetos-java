package br.com.e.feira.business.controller.persistence.interfaces;

import br.com.core.entity.FeirasTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Feiras {

    public void alterar(FeirasTO feirasTO);

    public void excluir(FeirasTO feirasTO);

    public void incluir(FeirasTO feirasTO);

    public FeirasTO consultar(FeirasTO feirasTO);

    public List<FeirasTO> listar(FeirasTO feirasTO);

}
