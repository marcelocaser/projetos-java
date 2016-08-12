package br.com.core.controller.business.interfaces;

import br.com.core.entity.EnderecosComplementosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface EnderecosComplementos {

    public void alterar(EnderecosComplementosTO complementosTO);

    public void excluir(EnderecosComplementosTO complementosTO);

    public void incluir(EnderecosComplementosTO complementosTO);

    public EnderecosComplementosTO consultar(EnderecosComplementosTO complementosTO);

    public List<EnderecosComplementosTO> listar(EnderecosComplementosTO complementosTO);

}
