package br.com.core.persistence;

import br.com.core.entity.UsuariosTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Repository
public class UsuariosPO extends Persistence<UsuariosTO> {

    public UsuariosPO() {
        setClazz(UsuariosTO.class);
    }

    @Transactional
    public void alterar(UsuariosTO usuariosTO) {
        update(usuariosTO);
    }

    @Transactional
    public void excluir(UsuariosTO usuariosTO) {
        delete(usuariosTO);
    }

    @Transactional
    public void incluir(UsuariosTO usuariosTO) {
        create(usuariosTO);
    }

    public UsuariosTO consultar(UsuariosTO usuariosTO) {
        return find(usuariosTO);
    }

    public List<UsuariosTO> listar(UsuariosTO usuariosTO) {
        return list(usuariosTO);
    }

    public List<UsuariosTO> listarUsuarios() {
        this.evict();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UsuariosTO> cq = cb.createQuery(UsuariosTO.class);
        Root<UsuariosTO> clientes = cq.from(UsuariosTO.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(clientes.get("exclusao")));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
