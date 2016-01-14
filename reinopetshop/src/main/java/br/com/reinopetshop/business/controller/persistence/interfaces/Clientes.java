package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.ClientesTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Clientes {
    
    public void alterar(ClientesTO clientesTO);

    public void excluir(ClientesTO clientesTO);

    public void incluir(ClientesTO clientesTO);

    public ClientesTO consultar(ClientesTO clientesTO);

    public List<ClientesTO> listar(ClientesTO clientesTO);
    
}
