package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.ClientesTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Clientes;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class ClientesPO extends Persistence<ClientesTO> implements Clientes {

    public ClientesPO() {
        setClazz(ClientesTO.class);
    }

    @Override
    @Transactional
    public void alterar(ClientesTO clientesTO) {
        update(clientesTO);
    }

    @Override
    @Transactional
    public void excluir(ClientesTO clientesTO) {
        delete(clientesTO);
    }

    @Override
    @Transactional
    public void incluir(ClientesTO clientesTO) {
        create(clientesTO);
    }

    @Override
    public ClientesTO consultar(ClientesTO clientesTO) {
        return find(clientesTO);
    }

    @Override
    public List<ClientesTO> listar(ClientesTO clientesTO) {
        return list(clientesTO);
    }

}
