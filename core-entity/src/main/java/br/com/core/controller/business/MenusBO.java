package br.com.core.controller.business;

import br.com.core.entity.MenusTO;
import br.com.core.entity.MenusUsuariosTO;
import br.com.core.persistence.interfaces.Menus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class MenusBO {

    @Autowired
    Menus persistencia;

    public void alterar(MenusTO menusTO) {
        this.persistencia.alterar(menusTO);
    }

    public void excluir(MenusTO menusTO) {
        this.persistencia.excluir(menusTO);
    }

    public void incluir(MenusTO menusTO) {
        this.persistencia.incluir(menusTO);
    }

    public MenusTO consultar(MenusTO menusTO) {
        return this.persistencia.consultar(menusTO);
    }

    public List<MenusTO> listar(MenusTO menusTO) {
        return this.persistencia.listar(menusTO);
    }

    public List<MenusUsuariosTO> listarMenuAtivo(MenusUsuariosTO menusUsuariosTO) {
        return this.persistencia.listarMenuAtivo(menusUsuariosTO);
    }

}
