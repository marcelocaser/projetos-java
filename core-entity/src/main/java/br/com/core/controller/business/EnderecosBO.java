package br.com.core.controller.business;

import br.com.core.entity.EnderecosTO;
import br.com.core.persistence.interfaces.Enderecos;
import br.com.core.util.CEPUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class EnderecosBO {

    @Autowired
    Enderecos persistencia;

    public void alterar(EnderecosTO enderecosTO) {
        this.persistencia.alterar(enderecosTO);
    }

    public EnderecosTO buscaUltimoId(EnderecosTO enderecosTO) {
        enderecosTO = this.persistencia.buscarUltimoId(enderecosTO);
        if (enderecosTO != null && enderecosTO.getId() != null) {
            return enderecosTO;
        }
        //Tratar exceção quando id for nulo.
        return null;

    }
    
    public Integer estatisticaTotalDeCEP() {
        return this.persistencia.estatisticaTotalDeCEP();
    }

    public void excluir(EnderecosTO enderecosTO) {
        this.persistencia.excluir(enderecosTO);
    }

    public void incluir(EnderecosTO enderecosTO) {
        buscaUltimoId(enderecosTO);
        this.persistencia.incluir(enderecosTO);
    }

    public EnderecosTO consultar(EnderecosTO enderecosTO) {
        return this.persistencia.consultar(enderecosTO);
    }
    
    public EnderecosTO consultarPeloCEP(String cep) {
        cep = CEPUtil.isCEPValido(cep);
        if (cep != null) {
            return this.persistencia.consultarPeloCEP(cep);
        }
        return null;
    }
    
    public List<EnderecosTO> listarLogradouro(String logradouro) {
        return this.persistencia.listarLogradouro(logradouro);
    }
    
    public List<EnderecosTO> listar(EnderecosTO enderecosTO) {
        return this.persistencia.listar(enderecosTO);
    }

}
