package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.AgendasTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Agendas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class AgendasPO extends Persistence<AgendasTO> implements Agendas {

    public AgendasPO() {
        setClazz(AgendasTO.class);
    }

    @Override
    @Transactional
    public void alterar(AgendasTO agendasTO) {
        update(agendasTO);
    }

    @Override
    @Transactional
    public void excluir(AgendasTO agendasTO) {
        deleteById(agendasTO.getId());
    }

    @Override
    @Transactional
    public void incluir(AgendasTO agendasTO) {
        create(agendasTO);
    }

    @Override
    public AgendasTO consultar(AgendasTO agendasTO) {
        return find(agendasTO);
    }

    @Override
    public List<AgendasTO> listar(AgendasTO agendasTO) {
        return list(agendasTO);
    }

    @Override
    public List<AgendasTO> listar() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(agenda.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        ParameterExpression<Date> dataInicio = cb.parameter(Date.class, "dataInicio");
        ParameterExpression<Date> dataFim = cb.parameter(Date.class, "dataFim");
        Expression<Date> data = agenda.get("data");
        cq.where(cb.between(data, dataInicio, dataFim));
        TypedQuery tq = getEntityManager().createQuery(cq);
        tq.setParameter("dataInicio", dataInicial);
        tq.setParameter("dataFim", dataFinal);
        return tq.getResultList();
    }

    @Override
    public List<AgendasTO> listarUltimosCompromissos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        cq.orderBy(cb.desc(agenda.get("id")));
        Query query = getEntityManager().createQuery(cq);
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
