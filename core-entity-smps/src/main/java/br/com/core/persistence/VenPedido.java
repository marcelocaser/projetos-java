package br.com.core.persistence;

import br.com.core.entity.VenPedidoTO;
import br.com.core.enumerator.EnumStatusPedido;
import java.util.List;

/**
 *
 * @author marce_000
 */
public interface VenPedido {

    public void alterar(VenPedidoTO clientesTO);

    public void excluir(VenPedidoTO clientesTO);

    public void incluir(VenPedidoTO clientesTO);

    public VenPedidoTO consultar(VenPedidoTO clientesTO);

    public List<VenPedidoTO> listar(VenPedidoTO clientesTO);
    
    public List<VenPedidoTO> listarPedidosPorCanalSetor(EnumStatusPedido status);

}
