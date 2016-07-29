package br.com.core.persistence;

import br.com.core.entity.EnderecosTO;
import br.com.core.persistence.interfaces.Enderecos;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Component
public class EnderecosPO extends Persistence<EnderecosTO> implements Enderecos {

    public EnderecosPO() {
        setClazz(EnderecosTO.class);
    }

    @Override
    @Transactional
    public void alterar(EnderecosTO enderecosTO) {
        update(enderecosTO);
    }

    @Override
    public EnderecosTO buscarUltimoId(EnderecosTO enderecosTO) {
        Query query = getEntityManager().createNativeQuery("SELECT TOP 1 id FROM enderecos ORDER BY id DESC");
        List results = query.getResultList();
        if (!results.isEmpty()) {
            enderecosTO.setId((Integer) results.get(0) + 1);
        }
        return enderecosTO;
    }

    @Override
    public Integer estatisticaTotalDeCEP() {
        return count();
    }

    @Override
    @Transactional
    public void excluir(EnderecosTO enderecosTO) {
        delete(enderecosTO);
    }

    @Override
    @Transactional
    public void incluir(EnderecosTO enderecosTO) {
        create(enderecosTO);
    }

    @Override
    public EnderecosTO consultar(EnderecosTO enderecosTO) {
        return find(enderecosTO);
    }

    @Override
    public EnderecosTO consultarPeloCEP(String cep) {
        try {
            return getEntityManager().createQuery("SELECT e FROM EnderecosTO e WHERE e.cep = :cep", EnderecosTO.class)
                    .setParameter("cep", cep)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }
        return null;
    }

    @Override
    public List<EnderecosTO> listarLogradouro(String logradouro) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<EnderecosTO> cq = cb.createQuery(EnderecosTO.class);
            Root<EnderecosTO> enderecos = cq.from(EnderecosTO.class);
            cq.where(cb.like(enderecos.get("logracompl"), "%" + logradouro + "%"));
            return getEntityManager().createQuery(cq).setMaxResults(5).getResultList();
        } catch (NoResultException ex) {
        }
        return null;
    }

    @Override
    public List<EnderecosTO> listar(EnderecosTO enderecosTO) {
        return list(enderecosTO);
    }

}
