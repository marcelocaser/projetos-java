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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
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
        delete(agendasTO);
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
    public List<AgendasTO> listarBanhoPorDataHora(Date data, Date hora) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(agenda.get("data"), cb.parameter(Date.class, "data")));
        predicates.add(cb.equal(agenda.get("hora"), cb.parameter(Date.class, "hora")));
        predicates.add(cb.isNull(agenda.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery tq = getEntityManager().createQuery(cq);
        tq.setParameter("data", data);
        tq.setParameter("hora", hora);
        return tq.getResultList();
    }

    @Override
    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        List<Predicate> predicates = new ArrayList<>();
        ParameterExpression<Date> dataInicio = cb.parameter(Date.class, "dataInicio");
        ParameterExpression<Date> dataFim = cb.parameter(Date.class, "dataFim");
        Expression<Date> data = agenda.get("data");
        predicates.add(cb.isNull(agenda.get("exclusao")));
        predicates.add(cb.between(data, dataInicio, dataFim));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery tq = getEntityManager().createQuery(cq);
        tq.setParameter("dataInicio", dataInicial);
        tq.setParameter("dataFim", dataFinal);
        return tq.getResultList();
    }

    @Override
    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal, Integer maximoDeAgendamentos) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        List<Predicate> predicates = new ArrayList<>();
        ParameterExpression<Date> dataInicio = cb.parameter(Date.class, "dataInicio");
        ParameterExpression<Date> dataFim = cb.parameter(Date.class, "dataFim");
        Expression<Date> data = agenda.get("data");
        predicates.add(cb.isNull(agenda.get("exclusao")));
        predicates.add(cb.between(data, dataInicio, dataFim));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery tq = getEntityManager().createQuery(cq).setMaxResults(maximoDeAgendamentos);
        tq.setParameter("dataInicio", dataInicial);
        tq.setParameter("dataFim", dataFinal);
        return tq.getResultList();
    }

    @Override
    public List<AgendasTO> listarUltimosCompromissos(Integer maximoDeAgendamentos) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AgendasTO> cq = cb.createQuery(AgendasTO.class);
        Root<AgendasTO> agenda = cq.from(AgendasTO.class);
        cq.orderBy(cb.desc(agenda.get("id")));
        cq.where(cb.isNull(agenda.get("exclusao")));
        Query query = getEntityManager().createQuery(cq).setMaxResults(maximoDeAgendamentos);
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
