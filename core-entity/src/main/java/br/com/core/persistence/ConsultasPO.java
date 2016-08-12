package br.com.core.persistence;

import br.com.core.entity.ConsultasTO;
import br.com.core.entity.UsuariosTO;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Repository
public class ConsultasPO extends Persistence<ConsultasTO> {

    public ConsultasPO() {
        setClazz(ConsultasTO.class);
    }

    @Transactional
    public void alterar(ConsultasTO consultasTO) {
        update(consultasTO);
    }

    public List<ConsultasTO> consultarEstatisticaCEPConsultados(UsuariosTO usuariosTO, Integer[] range) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ConsultasTO> cq = cb.createQuery(ConsultasTO.class);
        Root<ConsultasTO> consultasTO = cq.from(ConsultasTO.class);
        Join<ConsultasTO, UsuariosTO> totalConsultas = consultasTO.join("idUsuarios");
        cq.where(cb.equal(totalConsultas.get("id"), cb.parameter(Integer.class, "id")));
        cq.orderBy(cb.desc(consultasTO.get("id")));
        TypedQuery<ConsultasTO> q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        q.setParameter("id", usuariosTO.getId());
        return q.getResultList();
    }

    public Integer estatisticaTotalDeCEPDesdeCadastro(UsuariosTO usuariosTO) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ConsultasTO> consultasTO = cq.from(ConsultasTO.class);
        Join<ConsultasTO, UsuariosTO> totalConsultas = consultasTO.join("idUsuarios");
        cq.select(cb.count(consultasTO));
        cq.where(cb.equal(totalConsultas.get("id"), cb.parameter(Integer.class, "id")));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        q.setParameter("id", usuariosTO.getId());
        return (q.getSingleResult()).intValue();
    }

    @Transactional
    public void excluir(ConsultasTO consultasTO) {
        delete(consultasTO);
    }

    @Transactional
    public void incluir(ConsultasTO consultasTO) {
        create(consultasTO);
    }

    public ConsultasTO consultar(ConsultasTO consultasTO) {
        return find(consultasTO);
    }

    public List<ConsultasTO> listar(ConsultasTO consultasTO) {
        return list(consultasTO);
    }

}
