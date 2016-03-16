package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.ServicosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Servicos {

    public void alterar(ServicosTO servicosTO);

    public void excluir(ServicosTO servicosTO);

    public void incluir(ServicosTO servicosTO);

    public ServicosTO consultar(ServicosTO servicosTO);

    public List<ServicosTO> listar(ServicosTO servicosTO);
}
