package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.ClientesTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Clientes {

    public static final Character ATIVO = 'A';
    public static final Character INATIVO = 'I';
    public static final Character EXCLUIDO = 'E';

    public void alterar(ClientesTO clientesTO);

    public void excluir(ClientesTO clientesTO);

    public void incluir(ClientesTO clientesTO);

    public ClientesTO consultar(ClientesTO clientesTO);

    public List<ClientesTO> listar(ClientesTO clientesTO);

    public List<ClientesTO> listar();

    public List<ClientesTO> listarClientesPeloNome(String nome);

    public List<ClientesTO> listarUltimosClientes(Integer maximoDeClientes);

}
