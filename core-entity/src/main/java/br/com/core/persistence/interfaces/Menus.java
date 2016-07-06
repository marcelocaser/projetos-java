package br.com.core.persistence.interfaces;

import br.com.core.entity.MenusTO;
import br.com.core.entity.MenusUsuariosTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Menus {
    
    public static final Character STATUS_ATIVO = 'A';
    public static final Character MENU_PRINCIPAL = 'P';
    public static final Character MENU_ITEM = 'I';

    public void alterar(MenusTO menusTO);

    public void excluir(MenusTO menusTO);

    public void incluir(MenusTO menusTO);

    public MenusTO consultar(MenusTO menusTO);

    public List<MenusTO> listar(MenusTO menusTO);
    
    public List<MenusUsuariosTO> listarMenuAtivo(MenusUsuariosTO menusUsuariosTO);

}
