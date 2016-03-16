package br.com.reinopetshop.business.controller.persistence;

import br.com.core.entity.PessoasTO;
import br.com.core.persistence.Persistence;
import br.com.reinopetshop.business.controller.persistence.interfaces.Pessoas;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Component
public class PessoasPO extends Persistence<PessoasTO> implements Pessoas {

    public PessoasPO() {
        setClazz(PessoasTO.class);
    }

    @Override
    @Transactional
    public void alterar(PessoasTO pessoasTO) {
        update(pessoasTO);
    }

    @Override
    @Transactional
    public void excluir(PessoasTO pessoasTO) {
        delete(pessoasTO);
    }

    @Override
    @Transactional
    public void incluir(PessoasTO pessoasTO) {
        create(pessoasTO);
    }

    @Override
    public PessoasTO consultar(PessoasTO pessoasTO) {
        return find(pessoasTO);
    }

    @Override
    public List<PessoasTO> listar(PessoasTO pessoasTO) {
        return list(pessoasTO);
    }

    @Override
    public Date getDataAtual() {
        return getDateMySQL();
    }

}
