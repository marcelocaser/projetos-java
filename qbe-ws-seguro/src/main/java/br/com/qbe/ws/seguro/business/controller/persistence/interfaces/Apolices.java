package br.com.qbe.ws.seguro.business.controller.persistence.interfaces;

import br.com.qbe.entity.ApolicesTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Apolices {

    public void alterar(ApolicesTO apolicesTO);

    public void excluir(ApolicesTO apolicesTO);

    public void incluir(ApolicesTO apolicesTO);

    public ApolicesTO consultar(ApolicesTO apolicesTO);

    public List<ApolicesTO> listar(ApolicesTO apolicesTO);

}
