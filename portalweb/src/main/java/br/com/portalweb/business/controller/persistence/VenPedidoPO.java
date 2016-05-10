package br.com.portalweb.business.controller.persistence;

import br.com.core.entity.VenPedidoTO;
import br.com.core.enumerator.EnumStatusPedido;
import br.com.core.persistence.Persistence;
import br.com.portalweb.business.controller.persistence.interfaces.VenPedido;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce_000
 */
@Named
public class VenPedidoPO extends Persistence<VenPedidoTO> implements VenPedido {

    public VenPedidoPO() {
        setClazz(VenPedidoTO.class);
    }

    @Override
    @Transactional
    public void alterar(VenPedidoTO clientesTO) {
        update(clientesTO);
    }

    @Override
    @Transactional
    public void excluir(VenPedidoTO clientesTO) {
        delete(clientesTO);
    }

    @Override
    @Transactional
    public void incluir(VenPedidoTO clientesTO) {
        create(clientesTO);
    }

    @Override
    public VenPedidoTO consultar(VenPedidoTO clientesTO) {
        return find(clientesTO);
    }

    @Override
    public List<VenPedidoTO> listar(VenPedidoTO clientesTO) {
        return list(clientesTO);
    }

    @Override
    public List<VenPedidoTO> listarPedidosPorCanalSetor(EnumStatusPedido status) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<VenPedidoTO> venPedidoTO = cq.from(VenPedidoTO.class);
        Expression<String> canalnome = venPedidoTO.get("canalnome");
        Expression<Long> numero = cb.count(canalnome);
        cq.multiselect(canalnome.alias("canalnome"), numero.alias("numero"));
        cq.groupBy(venPedidoTO.get("canalnome"));
        cq.where(cb.equal(venPedidoTO.get("status"), cb.parameter(String.class, "status")));
        TypedQuery<Tuple> tq = getEntityManager().createQuery(cq);
        tq.setParameter("status", status.getNome());
        List<VenPedidoTO> venPedidoTOs = new ArrayList<>();
        for (Tuple t : tq.getResultList()) {
            VenPedidoTO pedido = new VenPedidoTO();
            pedido.setCanalnome((String) t.get("canalnome"));
            pedido.setNumero(new BigDecimal((Long) t.get("numero")));
            venPedidoTOs.add(pedido);
        }
        return venPedidoTOs;
    }
    
    public List<VenPedidoTO> listarPedidosPorCanalStatusQuantidade(EnumStatusPedido status) {
        return null;
    }

}
