package br.com.core.persistence;

import br.com.core.controller.business.interfaces.Menus;
import br.com.core.entity.MenusTO;
import br.com.core.entity.MenusUsuariosTO;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Repository
public class MenusPO extends Persistence<MenusTO> {

    public MenusPO() {
        setClazz(MenusTO.class);
    }

    @Transactional
    public void alterar(MenusTO menusTO) {
        update(menusTO);
    }

    @Transactional
    public void excluir(MenusTO menusTO) {
        delete(menusTO);
    }

    @Transactional
    public void incluir(MenusTO menusTO) {
        create(menusTO);
    }

    public MenusTO consultar(MenusTO menusTO) {
        return find(menusTO);
    }

    public List<MenusTO> listar(MenusTO menusTO) {
        return list(menusTO);
    }

    public List<MenusUsuariosTO> listarMenuAtivo(MenusUsuariosTO menusUsuariosTO) {
        if (menusUsuariosTO == null || menusUsuariosTO.getIdUsuario() == null) {
            return null;
        }
        this.evict();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MenusUsuariosTO> cq = cb.createQuery(MenusUsuariosTO.class);
        Root<MenusUsuariosTO> menusUsuarios = cq.from(MenusUsuariosTO.class);
        //Busca todos os menus ativos do usuário
        cq.where(cb.and(cb.equal(menusUsuarios.get("ativo"), cb.parameter(String.class, "ativo"))), cb.and(cb.equal(menusUsuarios.get("idUsuario"), cb.parameter(String.class, "idUsuario"))));
        cq.orderBy(cb.asc(menusUsuarios.get("idMenuPai")), cb.asc(menusUsuarios.get("ordem")));
        TypedQuery tq = getEntityManager().createQuery(cq);
        tq.setParameter("ativo", Menus.STATUS_ATIVO);
        tq.setParameter("idUsuario", menusUsuariosTO.getIdUsuario());
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
