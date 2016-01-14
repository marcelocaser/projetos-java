package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.ClientesTO;
import br.com.core.entity.PessoasTO;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.ClientesBO;
import java.util.List;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class ClientesBean extends ReinoPetController {

    @Autowired
    private EnderecoBean enderecoBean;
    @Autowired
    private ClientesBO clientesNegocio;
    private ClientesTO clientesTO;
    private List<ClientesTO> clientesTOs;
    private boolean skip;

    /* Métodos para tratamento do negócio. */
    public String consultar() {
        try {
            return "/app/clientes/clientesConsultar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String editar() {
        try {
            return "/app/clientes/clientesEditar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String excluir() {
        try {
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            setClientesTOs(clientesNegocio.listar(new ClientesTO()));
            return "/app/clientes/clientesListar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String novo() {
        clientesTO = new ClientesTO();
        clientesTO.setPessoasTO(new PessoasTO());
        //clientesTO.getPessoasTO().setEnderecosTOList(new ArrayList<>());
        
        enderecoBean.novo();

        return "/app/clientes/clientesNovo";
    }

    public String salvar() {
        try {
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    /**
     * Método usado para tratar os eventos, Next e Back (passos) do Wizard.
     */
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;	//reseta Wizard, no caso do usuário voltar.
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public ClientesTO getClientesTO() {
        return clientesTO;
    }

    public void setClientesTO(ClientesTO clientesTO) {
        this.clientesTO = clientesTO;
    }

    public List<ClientesTO> getClientesTOs() {
        return clientesTOs;
    }

    public void setClientesTOs(List<ClientesTO> clientesTOs) {
        this.clientesTOs = clientesTOs;
    }

}
