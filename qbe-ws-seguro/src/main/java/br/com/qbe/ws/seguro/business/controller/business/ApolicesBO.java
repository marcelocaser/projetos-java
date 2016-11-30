package br.com.qbe.ws.seguro.business.controller.business;

import br.com.qbe.entity.ApolicesTO;
import br.com.qbe.ws.seguro.business.controller.persistence.interfaces.Apolices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class ApolicesBO {
    
    @Autowired
    Apolices persistencia;
    
    public void alterar(ApolicesTO apolicesTO) {
        antesDeAlterar(apolicesTO);
        this.persistencia.alterar(apolicesTO);
    }

    private void antesDeAlterar(ApolicesTO apolicesTO) {
    }

    private void antesDeExcluir(ApolicesTO apolicesTO) {
    }

    public void excluir(ApolicesTO apolicesTO) {
        antesDeExcluir(apolicesTO);
        this.persistencia.alterar(apolicesTO);

    }

    public void incluir(ApolicesTO apolicesTO) {
        antesDeAlterar(apolicesTO);
        this.persistencia.incluir(apolicesTO);
    }

    public ApolicesTO consultar(ApolicesTO apolicesTO) {
        return this.persistencia.consultar(apolicesTO);
    }

    public List<ApolicesTO> listar(ApolicesTO apolicesTO) {
        return this.persistencia.listar(apolicesTO);
    }
    
}
