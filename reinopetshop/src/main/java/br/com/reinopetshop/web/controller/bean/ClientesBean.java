package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.ClientesTO;
import br.com.core.entity.PessoasTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.ClientesBO;
import java.util.ArrayList;
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
    private ClientesBO clientesNegocio;

    private EnderecosBean enderecosBean;
    private AnimaisBean animaisBean;
    private PessoasBean pessoasBean;
    private ClientesTO clientesTO;
    private List<ClientesTO> clientesTOs;
    private List<ClientesTO> ultimosClientesAdicionados;
    private Integer totalClientesCadastrados;
    private boolean skip;

    /* Métodos para tratamento do negócio. */
    public String consultar() {
        try {
            //ClientesTO cli = new ClientesTO(clientesTO.getId());
            //this.novo();
            clientesTO = clientesNegocio.consultar(new ClientesTO(clientesTO.getId()));
            this.getEnderecosBean().setEnderecosComplementosTOs(clientesTO.getPessoasTO().getEnderecosComplementosTOList());
            this.getAnimaisBean().setAnimaisTOs(clientesTO.getAnimaisTOList());
            return "/app/clientes/clientesConsultar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultar(Integer idCliente) {
        this.novo();
        clientesTO.setId(idCliente);
        return this.consultar();
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
            clientesNegocio.excluir(clientesTO);
//            this.getAnimaisBean().excluir();
//            this.getPessoasBean().excluir();
//            this.getEnderecosBean().excluir();
//            agendasBean.excluir();
            this.listarUltimosClientes();
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            //Cliente cadastrado. Atulizar os dados.
            if (clientesTO.getId() != null) {
                this.getPessoasBean().setPessoasTO(clientesTO.getPessoasTO());
                this.getPessoasBean().incluir();
                this.getEnderecosBean().incluir();
                //this.getAnimaisBean().incluir();
                setMessage("clientesAlteradoComSucesso", EnumTipoMensagem.INFO);
            } else {
                // Verifica se CPF não cadastrado!
                if (clientesTO.getPessoasTO().getCpf() != null) {
                    if (!this.clientesNegocio.isPessoaJaCadastrada(clientesTO)) {
                        this.getPessoasBean().setPessoasTO(clientesTO.getPessoasTO());
                        this.getPessoasBean().incluir();
                        //Verifica se inseriu nova pessoa.
                        if (this.getPessoasBean().getPessoasTO() != null && this.getPessoasBean().getPessoasTO().getId() != null) {
                            //Salva o endereço
                            this.getEnderecosBean().adicionarMaisEndereco();
                            this.getEnderecosBean().incluir();
                            this.clientesNegocio.incluir(clientesTO);
                            if (clientesTO.getId() != null) {
                                //Salva animais
                                this.getAnimaisBean().adicionarMaisAnimal();
                                this.getAnimaisBean().incluir();
                            } else {
                                setMessage("clientesNaoInserido", EnumTipoMensagem.ATENCAO);
                                return "";
                            }
                        } else {
                            setMessage("clientesPessoaNaoInserida", EnumTipoMensagem.ATENCAO);
                            return "";
                        }
                    } else {
                        setMessage("clientesJaCadastradoComEsseCPF", EnumTipoMensagem.ATENCAO);
                        return "";
                    }
                }
                setMessage("clientesCadastradoComSucesso", EnumTipoMensagem.INFO);
            }
            this.listar();
            this.listarUltimosClientes();
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            clientesTOs = clientesNegocio.listar();
            totalClientesCadastrados = clientesTOs.size();
            return "/app/clientes/clientesListar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public List<String> listarClientesPeloNome(String nome) {
        try {
            this.novo();
            List<ClientesTO> clientes = clientesNegocio.listarClientesPeloNome(nome);
            List<String> nomes = new ArrayList<>();
            for (ClientesTO cliente : clientes) {
                nomes.add(cliente.getPessoasTO().getNome());
            }
            return nomes;
        } catch (Exception e) {
            return null;
        }
    }

    public void listarUltimosClientes() {
        ultimosClientesAdicionados = clientesNegocio.listarUltimosClientes(getConfiguracaoDoSistema().getMaximoClientesPainel());
    }

    public String novo() {
        clientesTO = new ClientesTO();
        clientesTO.setPessoasTO(new PessoasTO());

        this.getEnderecosBean().novo();
        this.getPessoasBean().novo();
        this.getEnderecosBean().setEnderecosComplementosTOs(null);
        this.getAnimaisBean().novo();
        this.getAnimaisBean().setAnimaisTOs(new ArrayList<>());

        return "/app/clientes/clientesNovo";
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public EnderecosBean getEnderecosBean() {
        if (enderecosBean == null) {
            enderecosBean = findBean("enderecosBean");
        }
        return enderecosBean;
    }

    public void setEnderecosBean(EnderecosBean enderecosBean) {
        this.enderecosBean = enderecosBean;
    }

    public AnimaisBean getAnimaisBean() {
        if (animaisBean == null) {
            animaisBean = findBean("animaisBean");
        }
        return animaisBean;
    }

    public void setAnimaisBean(AnimaisBean animaisBean) {
        this.animaisBean = animaisBean;
    }

    public PessoasBean getPessoasBean() {
        if (pessoasBean == null) {
            pessoasBean = findBean("pessoasBean");
        }
        return pessoasBean;
    }

    public void setPessoasBean(PessoasBean pessoasBean) {
        this.pessoasBean = pessoasBean;
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

    public List<ClientesTO> getUltimosClientesAdicionados() {
        return ultimosClientesAdicionados;
    }

    public void setUltimosClientesAdicionados(List<ClientesTO> ultimosClientesAdicionados) {
        this.ultimosClientesAdicionados = ultimosClientesAdicionados;
    }

    public Integer getTotalClientesCadastrados() {
        return totalClientesCadastrados;
    }

    public void setTotalClientesCadastrados(Integer totalClientesCadastrados) {
        this.totalClientesCadastrados = totalClientesCadastrados;
    }

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

}
