package br.com.core.controller.business;

import br.com.core.entity.VenPedidoTO;
import br.com.core.enumerator.EnumStatusPedido;
import br.com.core.persistence.VenPedido;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marce_000
 */
@Named
public class VenPedidoBO {

    @Inject
    VenPedido persistencia;

    public void alterar(VenPedidoTO venPedidoTO) {
        this.persistencia.alterar(venPedidoTO);
    }

    public void excluir(VenPedidoTO venPedidoTO) {
        this.persistencia.excluir(venPedidoTO);
    }

    public void incluir(VenPedidoTO venPedidoTO) {
        this.persistencia.incluir(venPedidoTO);
    }

    public VenPedidoTO consultar(VenPedidoTO venPedidoTO) {
        return this.persistencia.consultar(venPedidoTO);
    }

    public List<VenPedidoTO> listar(VenPedidoTO venPedidoTO) {
        return this.persistencia.listar(venPedidoTO);
    }
    
    public List<VenPedidoTO> listarPedidosPorCanalSetor(EnumStatusPedido status) {
        return this.persistencia.listarPedidosPorCanalSetor(status);
    }

}
