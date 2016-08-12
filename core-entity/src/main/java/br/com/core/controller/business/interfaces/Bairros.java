package br.com.core.controller.business.interfaces;

import br.com.core.entity.BairrosTO;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Bairros {

    public void alterar(BairrosTO bairrosTO);

    public void excluir(BairrosTO bairrosTO);

    public void incluir(BairrosTO bairrosTO);

    public BairrosTO consultar(BairrosTO bairrosTO);

    public BairrosTO consultaBairrosPeloNome(String nome, String uf);

    public List<BairrosTO> listar(BairrosTO bairrosTO);

}
