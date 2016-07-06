package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.ClientesTO;
import br.com.core.entity.PessoasTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.ClientesBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private static final Integer maximoDeCliente = 6;
    @Autowired
    private EnderecosBean enderecosBean;
    @Autowired
    private AnimaisBean animaisBean;
    @Autowired
    private PessoasBean pessoasBean;
    @Autowired
    private AgendasBean agendasBean;
    @Autowired
    private ClientesBO clientesNegocio;
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
            enderecosBean.setEnderecosComplementosTOs(clientesTO.getPessoasTO().getEnderecosComplementosTOList());
            animaisBean.setAnimaisTOs(clientesTO.getAnimaisTOList());
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
            clientesNegocio.excluir(clientesTO);
//            animaisBean.excluir();
//            pessoasBean.excluir();
//            enderecosBean.excluir();
//            agendasBean.excluir();
            this.listarUltimosClientes();
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    @PostConstruct
    public void init() {
        this.listarUltimosClientes();
        this.listar();
    }

    public String incluir() {
        try {
            //Cliente cadastrado. Atulizar os dados.
            if (clientesTO.getId() != null) {
                pessoasBean.setPessoasTO(clientesTO.getPessoasTO());
                pessoasBean.incluir();
                enderecosBean.incluir();
                animaisBean.incluir();
                setMessage("clientesAlteradoComSucesso", EnumTipoMensagem.INFO);
            } else {
                // Verifica se CPF não cadastrado!
                if (clientesTO.getPessoasTO().getCpf() != null) {
                    if (!this.clientesNegocio.isPessoaJaCadastrada(clientesTO)) {
                        pessoasBean.setPessoasTO(clientesTO.getPessoasTO());
                        pessoasBean.incluir();
                        //Verifica se inseriu nova pessoa.
                        if (pessoasBean.getPessoasTO() != null && pessoasBean.getPessoasTO().getId() != null) {
                            //Salva o endereço
                            enderecosBean.adicionarMaisEndereco();
                            enderecosBean.incluir();
                            this.clientesNegocio.incluir(clientesTO);
                            if (clientesTO.getId() != null) {
                                //Salva animais
                                animaisBean.adicionarMaisAnimal();
                                animaisBean.incluir();
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
            this.init();
            return this.consultar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            clientesTOs = clientesNegocio.listarClientes();
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
        ultimosClientesAdicionados = clientesNegocio.listarUltimosClientes(maximoDeCliente);
    }

    public String novo() {
        clientesTO = new ClientesTO();
        clientesTO.setPessoasTO(new PessoasTO());

        enderecosBean.novo();
        enderecosBean.setEnderecosComplementosTOs(new ArrayList<>());
        animaisBean.novo();
        animaisBean.setAnimaisTOs(new ArrayList<>());

        return "/app/clientes/clientesNovo";
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public EnderecosBean getEnderecosBean() {
        return enderecosBean;
    }

    public void setEnderecosBean(EnderecosBean enderecosBean) {
        this.enderecosBean = enderecosBean;
    }

    public AnimaisBean getAnimaisBean() {
        return animaisBean;
    }

    public void setAnimaisBean(AnimaisBean animaisBean) {
        this.animaisBean = animaisBean;
    }

    public PessoasBean getPessoasBean() {
        return pessoasBean;
    }

    public void setPessoasBean(PessoasBean pessoasBean) {
        this.pessoasBean = pessoasBean;
    }

    public AgendasBean getAgendasBean() {
        return agendasBean;
    }

    public void setAgendasBean(AgendasBean agendasBean) {
        this.agendasBean = agendasBean;
    }

    public ClientesBO getClientesNegocio() {
        return clientesNegocio;
    }

    public void setClientesNegocio(ClientesBO clientesNegocio) {
        this.clientesNegocio = clientesNegocio;
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
