package br.com.core.controller.business;

import br.com.core.entity.WebMenusTO;
import br.com.core.entity.WebUsuariosTO;
import br.com.core.persistence.interfaces.WebMenus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class WebMenusBO {

    @Autowired
    WebMenus persistencia;

    public void alterar(WebMenusTO webMenusTO) {
        this.persistencia.alterar(webMenusTO);
    }

    public void excluir(WebMenusTO webMenusTO) {
        this.persistencia.excluir(webMenusTO);
    }

    public void incluir(WebMenusTO webMenusTO) {
        this.persistencia.incluir(webMenusTO);
    }

    public WebMenusTO consultar(WebMenusTO webMenusTO) {
        return this.persistencia.consultar(webMenusTO);
    }

    /*public WebMenusTO consultarPai(WebMenusTO webMenusTO) {
        if (webMenusTO.getWebMenusUsuariosTOList().size() > 0) {
            WebMenusUsuariosTO webMenusUsuariosTO = webMenusTO.getWebMenusUsuariosTOList().get(0);
            WebMenusTO menuPai = new WebMenusTO(webMenusUsuariosTO.getIdmenupai());
            return this.consultar(menuPai);
        }
        return null;
    }*/

    public List<WebMenusTO> listar(WebMenusTO webMenusTO) {
        return this.persistencia.listar(webMenusTO);
    }

    public List<WebMenusTO> listarMenuAtivo(WebUsuariosTO webUsuariosTO, Integer idMenuPai) {
        return this.persistencia.listarMenuAtivo(webUsuariosTO, idMenuPai);
    }

    public List<WebMenusTO> listarMenuPrincipal(WebUsuariosTO webUsuariosTO) {
        return this.persistencia.listarMenuPrincipal(webUsuariosTO);
    }

}
