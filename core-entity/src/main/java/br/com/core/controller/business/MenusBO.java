package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Menus;
import br.com.core.entity.MenusTO;
import br.com.core.entity.MenusUsuariosTO;
import br.com.core.persistence.MenusPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class MenusBO implements Menus {

    @Autowired
    MenusPO persistencia;

    @Override
    public void alterar(MenusTO menusTO) {
        this.persistencia.alterar(menusTO);
    }

    @Override
    public void excluir(MenusTO menusTO) {
        this.persistencia.excluir(menusTO);
    }

    @Override
    public void incluir(MenusTO menusTO) {
        this.persistencia.incluir(menusTO);
    }

    @Override
    public MenusTO consultar(MenusTO menusTO) {
        return this.persistencia.consultar(menusTO);
    }

    @Override
    public List<MenusTO> listar(MenusTO menusTO) {
        return this.persistencia.listar(menusTO);
    }

    @Override
    public List<MenusUsuariosTO> listarMenuAtivo(MenusUsuariosTO menusUsuariosTO) {
        return this.persistencia.listarMenuAtivo(menusUsuariosTO);
    }

}
