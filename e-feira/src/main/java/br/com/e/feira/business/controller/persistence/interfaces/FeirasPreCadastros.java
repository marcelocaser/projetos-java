package br.com.e.feira.business.controller.persistence.interfaces;

import br.com.core.entity.FeirasPreCadastrosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface FeirasPreCadastros {
    
    public void alterar(FeirasPreCadastrosTO feirasPreCadastrosTO);

    public void excluir(FeirasPreCadastrosTO feirasPreCadastrosTO);

    public void incluir(FeirasPreCadastrosTO feirasPreCadastrosTO);

    public FeirasPreCadastrosTO consultar(FeirasPreCadastrosTO feirasPreCadastrosTO);

    public List<FeirasPreCadastrosTO> listar(FeirasPreCadastrosTO feirasPreCadastrosTO);
}
