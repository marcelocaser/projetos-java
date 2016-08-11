package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.PessoasTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Pessoas {

    public void alterar(PessoasTO pessoasTO);

    public void excluir(PessoasTO pessoasTO);

    public void incluir(PessoasTO pessoasTO);

    public PessoasTO consultar(PessoasTO pessoasTO);

    public List<PessoasTO> listar(PessoasTO pessoasTO);

    public Date getDataAtual();

}
