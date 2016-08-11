package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.ClientesTO;
import br.com.core.entity.PessoasTO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Clientes;
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
public class ClientesBO {

    @Autowired
    Clientes persistencia;
    @Autowired
    Pessoas pessoasPersistencia;

    public void alterar(ClientesTO clientesTO) {
        this.persistencia.alterar(clientesTO);
    }

    private void antesDeAlterar(ClientesTO clientesTO) {
        clientesTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(ClientesTO clientesTO) {
        clientesTO.setExclusao(new Date());
        clientesTO.setStatus(Clientes.EXCLUIDO);
    }

    public ClientesTO consultar(ClientesTO clientesTO) {
        return this.persistencia.consultar(clientesTO);
    }

    public void excluir(ClientesTO clientesTO) {
        antesDeExcluir(clientesTO);
        this.persistencia.alterar(clientesTO);
    }

    public void incluir(ClientesTO clientesTO) {
        antesDeAlterar(clientesTO);
        clientesTO.setClienteDesde(new Date());
        clientesTO.setStatus(Clientes.ATIVO);
        this.persistencia.incluir(clientesTO);
    }

    public List<ClientesTO> listar(ClientesTO clientesTO) {
        return this.persistencia.listar(clientesTO);
    }

    public List<ClientesTO> listar() {
        return this.persistencia.listar();
    }

    public List<ClientesTO> listarClientesPeloNome(String nome) {
        return this.persistencia.listarClientesPeloNome(nome);
    }

    public List<ClientesTO> listarUltimosClientes(Integer maximoDeClientes) {
        return this.persistencia.listarUltimosClientes(maximoDeClientes);
    }

    public Boolean isPessoaJaCadastrada(ClientesTO clientesTO) {
        if (clientesTO != null) {
            PessoasTO pessoasTO = new PessoasTO();
            pessoasTO.setCpf(clientesTO.getPessoasTO().getCpf());
            pessoasTO = this.pessoasPersistencia.consultar(pessoasTO);
            if (pessoasTO != null && pessoasTO.getId() != null) {
                return true;
            }
        }
        return false;
    }

}
