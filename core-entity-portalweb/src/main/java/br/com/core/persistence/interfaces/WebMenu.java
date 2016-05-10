package br.com.core.persistence.interfaces;

import br.com.core.entity.WebMenuTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface WebMenu {

    public void alterar(WebMenuTO webMenuTO);

    public void excluir(WebMenuTO webMenuTO);

    public void incluir(WebMenuTO webMenuTO);

    public WebMenuTO consultar(WebMenuTO webMenuTO);

    public List<WebMenuTO> listar(WebMenuTO webMenuTO);

}
