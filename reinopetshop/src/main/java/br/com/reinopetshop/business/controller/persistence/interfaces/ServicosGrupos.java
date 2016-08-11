package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.ServicosGruposTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface ServicosGrupos {

    public void alterar(ServicosGruposTO servicosGruposTO);

    public void excluir(ServicosGruposTO servicosGruposTO);

    public void incluir(ServicosGruposTO servicosGruposTO);

    public ServicosGruposTO consultar(ServicosGruposTO servicosGruposTO);

    public List<ServicosGruposTO> listar(ServicosGruposTO servicosGruposTO);

}
