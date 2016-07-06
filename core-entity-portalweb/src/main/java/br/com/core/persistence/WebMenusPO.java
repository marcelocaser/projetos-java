package br.com.core.persistence;

import br.com.core.entity.WebMenusTO;
import br.com.core.entity.WebMenusUsuariosTO;
import br.com.core.entity.WebUsuariosTO;
import br.com.core.persistence.interfaces.WebMenus;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class WebMenusPO extends Persistence<WebMenusTO> implements WebMenus {

    public WebMenusPO() {
        setClazz(WebMenusTO.class);
    }

    @Override
    @Transactional
    public void alterar(WebMenusTO webMenuTO) {
        update(webMenuTO);
    }

    @Override
    @Transactional
    public void excluir(WebMenusTO webMenusTO) {
        delete(webMenusTO);
    }

    @Override
    @Transactional
    public void incluir(WebMenusTO webMenusTO) {
        create(webMenusTO);
    }

    @Override
    public WebMenusTO consultar(WebMenusTO webMenusTO) {
        return find(webMenusTO);
    }

    @Override
    public List<WebMenusTO> listar(WebMenusTO webMenusTO) {
        return list(webMenusTO);
    }

    @Override
    public List<WebMenusTO> listarMenuAtivo(WebUsuariosTO webUsuariosTO, Integer idMenuPai) {
        this.evict();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<WebMenusTO> cq = cb.createQuery(WebMenusTO.class);
        Root<WebMenusTO> webMenusTO = cq.from(WebMenusTO.class);
        //Join<WebMenusTO, WebMenusUsuariosTO> webMenusUsuariosTO = webMenusTO.joinList("webMenusUsuariosTOList");
        if (idMenuPai == null) {
            //Busca todos os menus ativos do usuário
            cq.where(/*cb.equal(webMenusTO.get("webUsuariosTO"), cb.parameter(WebUsuariosTO.class, "webUsuariosTO")),*/ cb.and(cb.equal(webMenusTO.get("ativo"), cb.parameter(String.class, "ativo"))));
        } else {
            //Busca todos os menus filhos pelo menu pai
            cq.where(/*cb.equal(webMenusTO.get("webUsuariosTO"), cb.parameter(WebUsuariosTO.class, "webUsuariosTO")),*/ cb.and(cb.equal(webMenusTO.get("idpai"), cb.parameter(Integer.class, "idpai")), cb.and(cb.equal(webMenusTO.get("ativo"), cb.parameter(String.class, "ativo")))));
        }
        //cq.orderBy(cb.asc(webMenusTO.get("ordem")), cb.asc(webMenusTO.get("idmenupai")));
        TypedQuery tq = getEntityManager().createQuery(cq);
        //tq.setParameter("webUsuariosTO", webUsuariosTO);
        tq.setParameter("ativo", STATUS_ATIVO);
        if (idMenuPai != null) {
            tq.setParameter("idpai", new WebMenusTO(idMenuPai));
        }
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<WebMenusTO> listarMenuPrincipal(WebUsuariosTO webUsuariosTO) {
        this.evict();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<WebMenusTO> cq = cb.createQuery(WebMenusTO.class);
        Root<WebMenusTO> webMenusTO = cq.from(WebMenusTO.class);
        Join<WebMenusTO, WebMenusUsuariosTO> webMenusUsuariosTO = webMenusTO.joinList("webMenusUsuariosTOList");
        //Busca todos os menus principais ativos do usuário
        cq.where(cb.equal(webMenusUsuariosTO.get("webUsuariosTO"), cb.parameter(WebUsuariosTO.class, "webUsuariosTO")), cb.and(cb.isNull(webMenusUsuariosTO.get("idmenupai")), cb.and(cb.equal(webMenusTO.get("ativo"), cb.parameter(String.class, "ativo")))));
        cq.orderBy(cb.asc(webMenusUsuariosTO.get("ordem")), cb.asc(webMenusUsuariosTO.get("idmenupai")));
        TypedQuery tq = getEntityManager().createQuery(cq);
        tq.setParameter("webUsuariosTO", webUsuariosTO);
        tq.setParameter("ativo", STATUS_ATIVO);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
