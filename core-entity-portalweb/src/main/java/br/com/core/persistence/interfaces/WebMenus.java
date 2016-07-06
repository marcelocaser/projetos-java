package br.com.core.persistence.interfaces;

import br.com.core.entity.WebMenusTO;
import br.com.core.entity.WebUsuariosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface WebMenus {
    
    public static final String STATUS_ATIVO = "A";
    
    public static final String MENU_PRINCIPAL = "P";
    
    public static final String MENU_FILHO = "F";

    public void alterar(WebMenusTO webMenusTO);

    public void excluir(WebMenusTO webMenusTO);

    public void incluir(WebMenusTO webMenusTO);

    public WebMenusTO consultar(WebMenusTO webMenusTO);

    public List<WebMenusTO> listar(WebMenusTO webMenusTO);

    public List<WebMenusTO> listarMenuAtivo(WebUsuariosTO webUsuariosTO, Integer idMenuPai);
    
    public List<WebMenusTO> listarMenuPrincipal(WebUsuariosTO webUsuariosTO);

}
