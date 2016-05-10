package br.com.portalweb.web.controller.bean;

import br.com.core.controller.business.WebMenuBO;
import br.com.core.entity.WebMenuTO;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class WebMenuBean {

    @Autowired
    private WebMenuBO menuNegocio;

    private MenuModel menuModel;

    /* Métodos para tratamento do negócio. */
    public void montaMenuUsuario() {
        menuModel = new DefaultMenuModel();
        List<WebMenuTO> webMenuTO = menuNegocio.listar(new WebMenuTO());

        for (WebMenuTO menuTO : webMenuTO) {
            DefaultSubMenu firstSubmenu = new DefaultSubMenu(menuTO.getNome(), menuTO.getIcone());
            for (WebMenuTO wmto : menuTO.getWebMenuTOList()) {
                DefaultMenuItem item = new DefaultMenuItem(wmto.getNome());
                item.setIcon(wmto.getIcone());
                item.setCommand(wmto.getAcao());
                item.setValue(wmto.getNome());
                firstSubmenu.addElement(item);
            }
            menuModel.addElement(firstSubmenu);
        }
    }

    @PostConstruct
    public void init() {
        //this.montaMenuUsuario();
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

}
