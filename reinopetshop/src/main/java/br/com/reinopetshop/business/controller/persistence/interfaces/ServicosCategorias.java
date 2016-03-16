package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.ServicosCategoriasTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface ServicosCategorias {
    
    public void alterar(ServicosCategoriasTO servicosCategoriasTO);

    public void excluir(ServicosCategoriasTO servicosCategoriasTO);

    public void incluir(ServicosCategoriasTO servicosCategoriasTO);

    public ServicosCategoriasTO consultar(ServicosCategoriasTO servicosCategoriasTO);

    public List<ServicosCategoriasTO> listar(ServicosCategoriasTO servicosCategoriasTO);
    
}
