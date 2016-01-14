package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.ClientesTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Clientes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class ClientesBO {

    @Autowired
    Clientes persistencia;

    public void alterar(ClientesTO clientesTO) {
        this.persistencia.alterar(clientesTO);
    }

    public void excluir(ClientesTO clientesTO) {
        this.persistencia.excluir(clientesTO);

    }

    public void incluir(ClientesTO clientesTO) {
        this.persistencia.incluir(clientesTO);
    }

    public ClientesTO consultar(ClientesTO clientesTO) {
        return this.persistencia.consultar(clientesTO);
    }

    public List<ClientesTO> listar(ClientesTO clientesTO) {
        return this.persistencia.listar(clientesTO);
    }

}
