package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.PessoasTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Pessoas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
public class PessoasBO {

    @Autowired
    Pessoas persistencia;

    public void alterar(PessoasTO pessoasTO) {
        this.persistencia.alterar(pessoasTO);
    }

    public void excluir(PessoasTO pessoasTO) {
        this.persistencia.excluir(pessoasTO);

    }

    public void incluir(PessoasTO pessoasTO) {
        this.persistencia.incluir(pessoasTO);
    }

    public PessoasTO consultar(PessoasTO pessoasTO) {
        return this.persistencia.consultar(pessoasTO);
    }

    public List<PessoasTO> listar(PessoasTO pessoasTO) {
        return this.persistencia.listar(pessoasTO);
    }
}
