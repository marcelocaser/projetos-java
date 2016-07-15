package br.com.reinopetshop.web.controller.bean;

import br.com.core.controller.business.MensagensBO;
import br.com.core.controller.business.MenusBO;
import br.com.core.entity.MenusTO;
import br.com.core.entity.MenusUsuariosTO;
import br.com.core.persistence.interfaces.Menus;
import br.com.reinopetshop.business.controller.ReinoPetController;
import java.util.List;
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
public class MenusBean extends ReinoPetController {

    @Autowired
    private MenusBO menuNegocio;
    @Autowired
    private MensagensBO mensagemNegocio;
    private MenuModel menuModel;
    private List<MenusUsuariosTO> menusUsuariosTOs;
    private List<MenusTO> menusTOs;

    /* Metodos para tratamento do negocio. */
    public String consultar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String editar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String excluir() {
        try {
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String novo() {
        return "/app/menus/menusNovo";
    }

    public void montaMenuUsuario() {
        this.geraMenu();
    }

    public void geraMenu() {
        menuModel = new DefaultMenuModel();
        MenusUsuariosTO menusUsuariosTO = new MenusUsuariosTO();
        menusUsuariosTO.setIdMenuUsuario(getUsuarioLogado());
        List<MenusUsuariosTO> listaMenu = menuNegocio.listarMenuAtivo(menusUsuariosTO);
        for (MenusUsuariosTO menu : listaMenu) {
            if (menu.getIdMenuPai() == null) {
                if (menu.getIdMenu().getTipo().equals(Menus.MENU_ITEM)) {
                    DefaultSubMenu submenu = new DefaultSubMenu();
                    submenu.setLabel(getMessage(menu.getIdMenu().getNome()));
                    submenu.setIcon(menu.getIdMenu().getIcone());
                    submenu.setRendered(menu.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
                    for (MenusUsuariosTO m : menu.getMenusUsuariosTOList()) {
                        if (m.getMenusUsuariosTOList().isEmpty()) {
                            DefaultMenuItem mi = new DefaultMenuItem();
                            mi.setValue(getMessage(m.getIdMenu().getNome()));
                            mi.setIcon(m.getIdMenu().getIcone());
                            mi.setCommand(m.getIdMenu().getAcao());
                            mi.setOnstart("PF('statusDialog').show()");
                            mi.setOnsuccess("PF('statusDialog').hide()");
                            mi.setRendered(m.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
                            submenu.getElements().add(mi);
                        } else {
                            DefaultSubMenu sm = new DefaultSubMenu();
                            sm.setLabel(getMessage(m.getIdMenu().getNome()));
                            sm.setIcon(m.getIdMenu().getIcone());
                            sm.setRendered(m.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
                            sm = geraSubmenu(m);
                            submenu.getElements().add(sm);
                        }
                    }
                    menuModel.addElement(submenu);
                } else {
                    //menu principal
                    DefaultMenuItem mi = new DefaultMenuItem();
                    mi.setValue(" " + getMessage(menu.getIdMenu().getNome()));
                    mi.setIcon(menu.getIdMenu().getIcone());
                    mi.setCommand(menu.getIdMenu().getAcao());
                    mi.setOnstart("PF('statusDialog').show()");
                    mi.setOnsuccess("PF('statusDialog').hide()");
                    mi.setRendered(menu.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
                    menuModel.addElement(mi);
                }
            }
        }
    }

    private DefaultSubMenu geraSubmenu(MenusUsuariosTO menu) {
        DefaultSubMenu submenu = new DefaultSubMenu();
        submenu.setLabel(getMessage(menu.getIdMenu().getNome()));
        submenu.setIcon(menu.getIdMenu().getIcone());
        submenu.setRendered(menu.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
        for (MenusUsuariosTO m : menu.getMenusUsuariosTOList()) {
            if (m.getMenusUsuariosTOList().isEmpty()) {
                DefaultMenuItem mi = new DefaultMenuItem();
                mi.setValue(getMessage(m.getIdMenu().getNome()));
                mi.setIcon(m.getIdMenu().getIcone());
                mi.setCommand(m.getIdMenu().getAcao());
                mi.setOnstart("PF('statusDialog').show()");
                mi.setOnsuccess("PF('statusDialog').hide()");
                mi.setRendered(m.getAtivo().equals(Menus.STATUS_ATIVO) ? Boolean.TRUE : Boolean.FALSE);
                submenu.getElements().add(mi);
            } else {
                submenu.getElements().add(geraSubmenu(m));
            }
        }
        return submenu;
    }
    
    public void atualizaMensagemResources() {
        mensagemNegocio.reload();
    }

    /* Metodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public List<MenusUsuariosTO> getMenusUsuariosTOs() {
        return menusUsuariosTOs;
    }

    public void setMenusUsuariosTOs(List<MenusUsuariosTO> menusUsuariosTOs) {
        this.menusUsuariosTOs = menusUsuariosTOs;
    }

    public List<MenusTO> getMenusTOs() {
        return menusTOs;
    }

    public void setMenusTOs(List<MenusTO> menusTOs) {
        this.menusTOs = menusTOs;
    }

}
