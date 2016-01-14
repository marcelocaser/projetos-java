package br.com.core.persistence.interfaces;

import br.com.core.entity.CidadesTO;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Cidades {

    public void alterar(CidadesTO cidadesTO);

    public void excluir(CidadesTO cidadesTO);

    public void incluir(CidadesTO cidadesTO);

    public CidadesTO consultar(CidadesTO cidadesTO);

    public List<CidadesTO> listar(CidadesTO cidadesTO);

}
