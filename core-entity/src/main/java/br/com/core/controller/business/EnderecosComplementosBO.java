package br.com.core.controller.business;

import br.com.core.entity.EnderecosComplementosTO;
import br.com.core.persistence.interfaces.EnderecosComplementos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class EnderecosComplementosBO {
    
     @Autowired
     EnderecosComplementos persistencia;

    public void alterar(EnderecosComplementosTO complementosTO) {
        this.persistencia.alterar(complementosTO);
    }
    
    private void antesDeAlterar(EnderecosComplementosTO complementosTO) {
        complementosTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(EnderecosComplementosTO complementosTO) {
        complementosTO.setExclusao(new Date());
    }

    public void excluir(EnderecosComplementosTO EnderecosComplementosTO) {
        antesDeExcluir(EnderecosComplementosTO);
        this.persistencia.excluir(EnderecosComplementosTO);

    }

    public void incluir(EnderecosComplementosTO EnderecosComplementosTO) {
        antesDeAlterar(EnderecosComplementosTO);
        this.persistencia.incluir(EnderecosComplementosTO);
    }

    public EnderecosComplementosTO consultar(EnderecosComplementosTO EnderecosComplementosTO) {
        return this.persistencia.consultar(EnderecosComplementosTO);
    }

    public List<EnderecosComplementosTO> listar(EnderecosComplementosTO EnderecosComplementosTO) {
        return this.persistencia.listar(EnderecosComplementosTO);
    }
    
}
