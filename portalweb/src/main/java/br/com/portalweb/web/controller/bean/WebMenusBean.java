package br.com.portalweb.web.controller.bean;

import br.com.core.controller.business.WebMenusBO;
import br.com.core.entity.WebMenusTO;
import br.com.core.entity.WebUsuariosTO;
import br.com.portalweb.business.controller.PortalWebController;
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
public class WebMenusBean extends PortalWebController {

    @Autowired
    private WebMenusBO menuNegocio;
    private MenuModel menuModel;
    //private DefaultSubMenu defaultSubMenu;
    //private DefaultSubMenu subMenu;
    private List<WebMenusTO> menusAtivosDoUsuario;
    private WebUsuariosTO webUsuariosTO;

    /* Métodos para tratamento do negócio. */
    public void montaMenuUsuario() {
        this.geraMenu();
    }

    public void geraMenu() {
        menuModel = new DefaultMenuModel();
        webUsuariosTO = new WebUsuariosTO(new Integer("1"));
        List<WebMenusTO> listaMenu = menuNegocio.listar(new WebMenusTO()); // Substituir o listaTodos() pelos menus permitidos
        for (WebMenusTO menu : listaMenu) {
            if (menu.getIdpai() == null) {
                DefaultSubMenu submenu = new DefaultSubMenu();
                submenu.setLabel(menu.getNome());
                WebMenusTO mPai = new WebMenusTO();
                mPai.setIdpai(menu);
                for (WebMenusTO m : menuNegocio.listar(mPai)) {
                    if (m.getWebMenusTOList().isEmpty()) {
                        DefaultMenuItem mi = new DefaultMenuItem();
                        mi.setValue(m.getNome());
                        mi.setIcon(m.getIcone());
                        submenu.getElements().add(mi);
                    } else {
                        DefaultSubMenu sm = new DefaultSubMenu();
                        sm.setLabel(m.getNome());
                        sm = geraSubmenu(m);
                        submenu.getElements().add(sm);
                    }
                }
                menuModel.addElement(submenu);
            }
        }
    }

    public DefaultSubMenu geraSubmenu(WebMenusTO menu) {
        DefaultSubMenu submenu = new DefaultSubMenu();
        submenu.setLabel(menu.getNome());
        WebMenusTO mPai = new WebMenusTO();
        mPai.setIdpai(menu);
        for (WebMenusTO m : menuNegocio.listar(mPai)) {
            if (m.getWebMenusTOList().isEmpty()) {
                DefaultMenuItem mi = new DefaultMenuItem();
                mi.setValue(m.getNome());
                submenu.getElements().add(mi);
            } else {
                submenu.getElements().add(geraSubmenu(m));
            }
        }
        return submenu;
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

    public List<WebMenusTO> getMenusAtivosDoUsuario() {
        return menusAtivosDoUsuario;
    }

    public void setMenusAtivosDoUsuario(List<WebMenusTO> menusAtivosDoUsuario) {
        this.menusAtivosDoUsuario = menusAtivosDoUsuario;
    }

}
