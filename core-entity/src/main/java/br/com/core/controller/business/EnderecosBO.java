package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Enderecos;
import br.com.core.entity.EnderecosTO;
import br.com.core.persistence.EnderecosPO;
import br.com.core.util.CEPUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class EnderecosBO implements Enderecos {

    @Autowired
    EnderecosPO persistencia;

    @Override
    public void alterar(EnderecosTO enderecosTO) {
        this.persistencia.alterar(enderecosTO);
    }

    @Override
    public EnderecosTO buscarUltimoId(EnderecosTO enderecosTO) {
        enderecosTO = this.persistencia.buscarUltimoId(enderecosTO);
        if (enderecosTO != null && enderecosTO.getId() != null) {
            return enderecosTO;
        }
        //Tratar exceção quando id for nulo.
        return null;

    }

    @Override
    public Integer estatisticaTotalDeCEP() {
        return this.persistencia.estatisticaTotalDeCEP();
    }

    @Override
    public void excluir(EnderecosTO enderecosTO) {
        this.persistencia.excluir(enderecosTO);
    }

    @Override
    public void incluir(EnderecosTO enderecosTO) {
        this.buscarUltimoId(enderecosTO);
        this.persistencia.incluir(enderecosTO);
    }

    @Override
    public EnderecosTO consultar(EnderecosTO enderecosTO) {
        return this.persistencia.consultar(enderecosTO);
    }

    @Override
    public EnderecosTO consultarPeloCEP(String cep) {
        cep = CEPUtil.isCEPValido(cep);
        if (cep != null) {
            return this.persistencia.consultarPeloCEP(cep);
        }
        return null;
    }

    @Override
    public List<EnderecosTO> listarLogradouro(String logradouro) {
        return this.persistencia.listarLogradouro(logradouro);
    }

    @Override
    public List<EnderecosTO> listar(EnderecosTO enderecosTO) {
        return this.persistencia.listar(enderecosTO);
    }

}
