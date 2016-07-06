package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.ClientesTO;
import br.com.core.entity.PessoasTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    /**
     * Lista os clientes ativos no sistema Será considerado os status de Ativo e
     * Inativo. Cliente com o status de excluído e / ou data de exclusão
     * informada não será listado.
     *
     * @return
     */
    @Override
    public List<ClientesTO> listarClientes() {
        this.evict();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClientesTO> cq = cb.createQuery(ClientesTO.class);
        Root<ClientesTO> clientes = cq.from(ClientesTO.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(clientes.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<ClientesTO> listarClientesPeloNome(String nome) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClientesTO> cq = cb.createQuery(ClientesTO.class);
        Root<ClientesTO> clientes = cq.from(ClientesTO.class);
        Join<ClientesTO, PessoasTO> pessoas = clientes.join("pessoasTO");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(pessoas.get("nome"), nome.toUpperCase() + "%"));
        predicates.add(cb.isNull(clientes.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<ClientesTO> listarUltimosClientes(Integer maximoDeClientes) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClientesTO> cq = cb.createQuery(ClientesTO.class);
        Root<ClientesTO> clientes = cq.from(ClientesTO.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(clientes.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        cq.orderBy(cb.desc(clientes.get("id")));
        Query query = getEntityManager().createQuery(cq).setMaxResults(maximoDeClientes);
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
