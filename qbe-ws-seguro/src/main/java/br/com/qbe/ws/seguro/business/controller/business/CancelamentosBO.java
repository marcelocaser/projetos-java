package br.com.qbe.ws.seguro.business.controller.business;

import br.com.qbe.bean.Cancelamentos.Cancelamento;
import br.com.qbe.entity.CancelamentosTO;
import br.com.qbe.entity.TiposCancelamentosTO;
import br.com.qbe.ws.seguro.business.controller.persistence.interfaces.Cancelamentos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class CancelamentosBO {
    
    @Autowired
    Cancelamentos persistencia;
    
    public void alterar(CancelamentosTO cancelamentosTO) {
        antesDeAlterar(cancelamentosTO);
        this.persistencia.alterar(cancelamentosTO);
    }

    private void antesDeAlterar(CancelamentosTO cancelamentosTO) {
    }

    private void antesDeExcluir(CancelamentosTO cancelamentosTO) {
    }
    
    public void excluir(CancelamentosTO cancelamentosTO) {
        antesDeExcluir(cancelamentosTO);
        this.persistencia.alterar(cancelamentosTO);

    }
    
    public void incluir(CancelamentosTO cancelamentosTO) {
        this.persistencia.incluir(cancelamentosTO);
    }
    
    public void incluir(Cancelamento cancelamento) {
        CancelamentosTO cancelamentosTO = new CancelamentosTO();
        cancelamentosTO.setCnpjCpf(cancelamento.getCnpjCpf());
        cancelamentosTO.setCriacao(new Date());
        cancelamentosTO.setIdTipoCancelamento(new TiposCancelamentosTO(new Integer(cancelamento.getTipoCancelamento())));
        cancelamentosTO.setNumeroBilhete(new Integer(cancelamento.getNumeroBilhete()));
        cancelamentosTO.setTipoArquivo(cancelamento.getTipoArquivo());
        this.incluir(cancelamentosTO);
    }

    public CancelamentosTO consultar(CancelamentosTO cancelamentosTO) {
        return this.persistencia.consultar(cancelamentosTO);
    }

    public List<CancelamentosTO> listar(CancelamentosTO cancelamentosTO) {
        return this.persistencia.listar(cancelamentosTO);
    }
    
}
