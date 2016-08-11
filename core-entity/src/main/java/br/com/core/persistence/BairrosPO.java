package br.com.core.persistence;

import br.com.core.entity.BairrosTO;
import br.com.core.persistence.interfaces.Bairros;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Repository
public class BairrosPO extends Persistence<BairrosTO> implements Bairros {

    public BairrosPO() {
        setClazz(BairrosTO.class);
    }

    @Override
    @Transactional
    public void alterar(BairrosTO bairrosTO) {
        update(bairrosTO);
    }

    @Override
    @Transactional
    public void excluir(BairrosTO bairrosTO) {
        delete(bairrosTO);
    }

    @Override
    @Transactional
    public void incluir(BairrosTO bairrosTO) {
        create(bairrosTO);
    }

    @Override
    public BairrosTO consultar(BairrosTO bairrosTO) {
        return find(bairrosTO);
    }

    @Override
    public BairrosTO consultaBairrosPeloNome(String nome, String uf) {
        try {
            return getEntityManager().createQuery("SELECT b FROM BairrosTO b WHERE b.nome LIKE :nome", BairrosTO.class)
                    .setParameter("nome", nome)
                    .setParameter("uf", uf)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }
        return null;
    }

    @Override
    public List<BairrosTO> listar(BairrosTO bairrosTO) {
        return list(bairrosTO);
    }

}
