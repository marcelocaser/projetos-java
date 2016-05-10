package br.com.core.persistence;

import br.com.core.entity.WebMenuTO;
import br.com.core.persistence.interfaces.WebMenu;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class WebMenuPO extends Persistence<WebMenuTO> implements WebMenu {

    public WebMenuPO() {
        setClazz(WebMenuTO.class);
    }

    @Override
    @Transactional
    public void alterar(WebMenuTO webMenuTO) {
        update(webMenuTO);
    }

    @Override
    @Transactional
    public void excluir(WebMenuTO webMenuTO) {
        delete(webMenuTO);
    }

    @Override
    @Transactional
    public void incluir(WebMenuTO webMenuTO) {
        create(webMenuTO);
    }

    @Override
    public WebMenuTO consultar(WebMenuTO webMenuTO) {
        return find(webMenuTO);
    }

    @Override
    public List<WebMenuTO> listar(WebMenuTO webMenuTO) {
        return list(webMenuTO);
    }

}
