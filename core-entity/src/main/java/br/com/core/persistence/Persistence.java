package br.com.core.persistence;

import br.com.core.exception.NegocioException;
import br.com.core.util.DateUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marcelocaser
 * @param <T>
 */
@Repository
public abstract class Persistence<T extends Serializable> {

    private Class< T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * @return the entityManagerM
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public final void setClazz(Class< T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    protected T findOne(long id) {
        return getEntityManager().find(clazz, id);
    }

    protected T findOne(String id) {
        return getEntityManager().find(clazz, id);
    }

    protected List< T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected T find(T entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(clazz);
        Root root = query.from(clazz);
        List<Predicate> predicates = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!Modifier.isFinal(f.getModifiers()) && f.get(entity) != null && f.get(entity) != "") {
                    //Desconsidera a consulta com campo do tipo TemporalType.TIMESTAMP.
                    if (!(f.getAnnotation(Temporal.class) instanceof Temporal)) {
                        predicates.add(cb.equal(root.get(f.getName()), f.get(entity)));
                    } else {
                        if (f.getAnnotation(Temporal.class).value() != TemporalType.TIMESTAMP) {
                            predicates.add(cb.equal(root.get(f.getName()), f.get(entity)));
                        }
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                //tratar erro;
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        try {
            return (T) getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    protected List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    protected List<T> list(T entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(clazz);
        Root root = query.from(clazz);
        List<Predicate> predicates = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!Modifier.isFinal(f.getModifiers()) && f.get(entity) != null) {
                    predicates.add(cb.equal(root.get(f.getName()), f.get(entity)));
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                //tratar erro;
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        try {
            return getEntityManager().createQuery(query).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    protected void create(T entity) {
        getEntityManager().persist(entity);
    }

    protected T update(T entity) {
        entity = getEntityManager().merge(entity);
        return entity;
    }

    protected void evict() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    protected void delete(T entity) {
        getEntityManager().remove(entity);
    }

    protected void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected void detach(T entity) {
        getEntityManager().detach(entity);
    }

    protected void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    protected void clear() {
        getEntityManager().clear();
    }

    protected Integer count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(clazz);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected Object max(String field) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root rt = cq.from(clazz);
        cq.select(getEntityManager().getCriteriaBuilder().max(rt.get(field)));
        Query q = getEntityManager().createQuery(cq);
        return (Object) q.getSingleResult();
    }

    protected Date getDateToAccess() {
        Query query = getEntityManager().createNativeQuery("SELECT Date() CurrentDateTime from dual");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getTimeToAccess() {
        Query query = getEntityManager().createNativeQuery("SELECT Time() CurrentDateTime from dual");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getDateTimeToAccess() {
        Query query = getEntityManager().createNativeQuery("SELECT Now() CurrentDateTime from dual");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getDateToJavaDB() {
        Query query = getEntityManager().createNativeQuery("VALUES CURRENT DATE");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getTimeToJavaDB() {
        Query query = getEntityManager().createNativeQuery("VALUES CURRENT TIME");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getDateTimeToJavaDB() {
        Query query = getEntityManager().createNativeQuery("VALUES CURRENT TIMESTAMP");
        Date date = (Date) query.getSingleResult();
        return date;
    }

    protected Date getDateTimeToOracle(String formato) throws NegocioException {
        Query query = getEntityManager().createNativeQuery("SELECT TO_CHAR(SYSDATE, '" + formato + "') from dual");
        String dataHora = (String) query.getSingleResult();
        if (formato != null && formato.equals(DateUtil.HORA_ORACLE_24H)) {
            formato = DateUtil.HORA;
        }
        return DateUtil.toDataOuHora(dataHora, formato);
    }
}
