package br.com.core.controller.business;

import br.com.core.entity.WebMenuTO;
import br.com.core.persistence.interfaces.WebMenu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class WebMenuBO {

    @Autowired
    WebMenu persistencia;

    public void alterar(WebMenuTO webMenuTO) {
        this.persistencia.alterar(webMenuTO);
    }

    public void excluir(WebMenuTO webMenuTO) {
        this.persistencia.excluir(webMenuTO);
    }

    public void incluir(WebMenuTO webMenuTO) {
        this.persistencia.incluir(webMenuTO);
    }

    public WebMenuTO consultar(WebMenuTO webMenuTO) {
        return this.persistencia.consultar(webMenuTO);
    }

    public List<WebMenuTO> listar(WebMenuTO webMenuTO) {
        return this.persistencia.listar(webMenuTO);
    }

}
