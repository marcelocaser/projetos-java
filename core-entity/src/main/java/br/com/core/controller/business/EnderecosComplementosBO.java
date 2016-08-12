package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.EnderecosComplementos;
import br.com.core.entity.EnderecosComplementosTO;
import br.com.core.persistence.EnderecosComplementosPO;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class EnderecosComplementosBO implements EnderecosComplementos {

    @Autowired
    EnderecosComplementosPO persistencia;

    @Override
    public void alterar(EnderecosComplementosTO complementosTO) {
        this.persistencia.alterar(complementosTO);
    }

    private void antesDeAlterar(EnderecosComplementosTO complementosTO) {
        complementosTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(EnderecosComplementosTO complementosTO) {
        complementosTO.setExclusao(new Date());
    }

    @Override
    public void excluir(EnderecosComplementosTO EnderecosComplementosTO) {
        antesDeExcluir(EnderecosComplementosTO);
        this.persistencia.excluir(EnderecosComplementosTO);

    }

    @Override
    public void incluir(EnderecosComplementosTO EnderecosComplementosTO) {
        antesDeAlterar(EnderecosComplementosTO);
        this.persistencia.incluir(EnderecosComplementosTO);
    }

    @Override
    public EnderecosComplementosTO consultar(EnderecosComplementosTO EnderecosComplementosTO) {
        return this.persistencia.consultar(EnderecosComplementosTO);
    }

    @Override
    public List<EnderecosComplementosTO> listar(EnderecosComplementosTO EnderecosComplementosTO) {
        return this.persistencia.listar(EnderecosComplementosTO);
    }

}
