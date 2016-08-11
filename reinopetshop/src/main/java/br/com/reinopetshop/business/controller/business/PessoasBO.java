package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.PessoasTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Pessoas;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class PessoasBO {

    @Autowired
    Pessoas persistencia;

    public void alterar(PessoasTO pessoasTO) {
        antesDeAlterar(pessoasTO);
        this.persistencia.alterar(pessoasTO);
    }

    private void antesDeAlterar(PessoasTO pessoasTO) {
        pessoasTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(PessoasTO pessoasTO) {
        pessoasTO.setExclusao(new Date());
    }

    public void excluir(PessoasTO pessoasTO) {
        antesDeExcluir(pessoasTO);
        this.persistencia.excluir(pessoasTO);

    }

    public void incluir(PessoasTO pessoasTO) {
        antesDeAlterar(pessoasTO);
        this.persistencia.incluir(pessoasTO);
    }

    public PessoasTO consultar(PessoasTO pessoasTO) {
        return this.persistencia.consultar(pessoasTO);
    }

    public List<PessoasTO> listar(PessoasTO pessoasTO) {
        return this.persistencia.listar(pessoasTO);
    }

    public Date getDataAtual() {
        return this.persistencia.getDataAtual();
    }
}
