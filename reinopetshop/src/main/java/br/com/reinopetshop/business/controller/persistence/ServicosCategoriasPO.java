package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.ServicosCategoriasTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.ServicosCategorias;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class ServicosCategoriasPO extends Persistence<ServicosCategoriasTO> implements ServicosCategorias {

    public ServicosCategoriasPO() {
        setClazz(ServicosCategoriasTO.class);
    }

    @Override
    @Transactional
    public void alterar(ServicosCategoriasTO servicosCategoriasTO) {
        update(servicosCategoriasTO);
    }

    @Override
    @Transactional
    public void excluir(ServicosCategoriasTO servicosCategoriasTO) {
        delete(servicosCategoriasTO);
    }

    @Override
    @Transactional
    public void incluir(ServicosCategoriasTO servicosCategoriasTO) {
        create(servicosCategoriasTO);
    }

    @Override
    public ServicosCategoriasTO consultar(ServicosCategoriasTO servicosCategoriasTO) {
        return find(servicosCategoriasTO);
    }

    @Override
    public List<ServicosCategoriasTO> listar(ServicosCategoriasTO servicosCategoriasTO) {
        return list(servicosCategoriasTO);
    }

}
