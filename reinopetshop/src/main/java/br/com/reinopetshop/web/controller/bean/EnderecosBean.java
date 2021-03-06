package br.com.reinopetshop.web.controller.bean;

import br.com.core.bean.RetornoCEP;
import br.com.core.controller.business.CidadesBO;
import br.com.core.controller.business.EnderecosComplementosBO;
import br.com.core.controller.business.EnderecosBO;
import br.com.core.entity.CidadesTO;
import br.com.core.entity.EnderecosTO;
import br.com.core.entity.EstadosTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.util.CEPUtil;
import br.com.core.util.RestUtil;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.core.controller.business.EstadosBO;
import br.com.core.entity.BairrosTO;
import br.com.core.entity.EnderecosComplementosTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class EnderecosBean extends ReinoPetController {

    @Autowired
    private EnderecosBO enderecosNegocio;
    @Autowired
    private EstadosBO estadosNegocio;
    @Autowired
    private CidadesBO cidadesNegocio;
    @Autowired
    private EnderecosComplementosBO complementosNegocio;
    private PessoasBean pessoasBean;
    private EnderecosTO enderecosTO;
    private EnderecosComplementosTO enderecosComplementosTO;
    private List<EnderecosTO> enderecosTOs;
    private List<EnderecosComplementosTO> enderecosComplementosTOs;
    private List<EstadosTO> estadosTOs;
    private List<CidadesTO> cidadesTOs;
    private String idEstado;
    private String idCidade;

    private static final String URL_WS_CEP = "http://localhost:8080/cep/ws/consultar.json?cep=[cep]&chave=[chave]";

    /* Métodos para tratamento do negócio. */
    public String adicionarMaisEndereco() {
        try {
            if (enderecosComplementosTO.getCep() != null) {
                if (enderecosComplementosTOs.stream().filter((p) -> p.getCep().equals(enderecosComplementosTO.getCep())).collect(Collectors.toList()).size() > 0) {
                    setMessage("enderecosJaCadastrado", EnumTipoMensagem.ATENCAO);
                    return "";
                }
            }
            //Adiciona pessoa aos endereço.
            if (this.getPessoasBean().getPessoasTO() != null && this.getPessoasBean().getPessoasTO().getId() != null) {
                enderecosComplementosTO.getPessoasTOList().add(this.getPessoasBean().getPessoasTO());
                for (int i = 0; i < enderecosComplementosTOs.size(); i++) {
                    enderecosComplementosTOs.get(i).getPessoasTOList().add(this.getPessoasBean().getPessoasTO());
                }
            }
            enderecosComplementosTOs.add(enderecosComplementosTO);
            setMessage("enderecosAdicionado", EnumTipoMensagem.INFO);
            this.novo();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultarCEP() {
        try {
            if (enderecosComplementosTO != null) {
                enderecosComplementosTO.setCep(CEPUtil.isCEPValido(enderecosComplementosTO.getCep()));
                if (enderecosComplementosTO.getCep() != null) {
                    RetornoCEP retornoCEP = new RetornoCEP();
                    retornoCEP = (RetornoCEP) RestUtil.buscaRetornoRestSpring(retornoCEP, URL_WS_CEP.replace("[cep]", enderecosComplementosTO.getCep()).replace("[chave]", getUsuarioLogado().getChave()));
                    if (retornoCEP != null && !retornoCEP.getStatus().equals(CEPUtil.ERRO)) {
                        enderecosComplementosTO.setIdEndereco(enderecosNegocio.consultar(new EnderecosTO(retornoCEP.getIdEndereco())));
                        if (enderecosComplementosTO.getIdEndereco() != null && enderecosComplementosTO.getIdEndereco().getIdCidade() != null && enderecosComplementosTO.getIdEndereco().getIdCidade().getIdEstado() != null) {
                            idEstado = enderecosComplementosTO.getIdEndereco().getIdCidade().getIdEstado().getId().toString();
                            this.listarCidades();
                            idCidade = enderecosComplementosTO.getIdEndereco().getIdCidade().getId().toString();
                        } else {
                            this.novo();
                            setMessage("enderecosErroConsultarCep", EnumTipoMensagem.ERRO);
                        }
                    } else {
                        this.novo();
                        setMessage("enderecosCepNaoExiste", EnumTipoMensagem.INFO);
                    }
                }
            } else {
                setMessage("enderecosCepInvalido", EnumTipoMensagem.ERRO);
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultarLogradouro() {
        try {
            enderecosTOs = enderecosNegocio.listarLogradouro(enderecosTO.getLogracompl());
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String editar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void enderecoSelected(EnderecosTO endereco) {
        enderecosComplementosTO.setCep(endereco.getCep());
        enderecosComplementosTO.setIdEndereco(endereco);
        idEstado = enderecosComplementosTO.getIdEndereco().getIdCidade().getIdEstado().getId().toString();
        this.listarCidades();
        idCidade = enderecosComplementosTO.getIdEndereco().getIdCidade().getId().toString();
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
            for (EnderecosComplementosTO complementosTO : enderecosComplementosTOs) {
                if (complementosTO.getId() == null) {
                    complementosNegocio.incluir(complementosTO);
                } else {
                    complementosNegocio.alterar(complementosTO);
                }
            }
            enderecosComplementosTOs = null;
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void listarCidades() {
        if (idEstado != null && !idEstado.isEmpty()) {
            CidadesTO cidadesTO = new CidadesTO();
            cidadesTO.setIdEstado(new EstadosTO(new Integer(idEstado)));
            cidadesTOs = cidadesNegocio.listar(cidadesTO);
        } else {
            this.novo();
        }
    }

    public void listarEstados() {
        estadosTOs = estadosNegocio.listar(new EstadosTO());
    }

    public String removerMaisEndereco() {
        try {
            enderecosComplementosTOs.removeIf(p -> p.getCep().contains(enderecosComplementosTO.getCep()));
            setMessage("enderecosRemovido", EnumTipoMensagem.INFO);
            this.novo();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String novo() {
        enderecosTO = new EnderecosTO();
        enderecosTOs = new ArrayList<>();
        enderecosComplementosTO = new EnderecosComplementosTO();
        enderecosComplementosTO.setPessoasTOList(new ArrayList<>());
        enderecosComplementosTO.setIdEndereco(new EnderecosTO());
        enderecosComplementosTO.getIdEndereco().setIdBairro(new BairrosTO());
        enderecosComplementosTO.getIdEndereco().setIdCidade(new CidadesTO());
        idCidade = null;
        idEstado = null;
        return "";
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public PessoasBean getPessoasBean() {
        if (pessoasBean == null) {
            pessoasBean = findBean("pessoasBean");
        }
        return pessoasBean;
    }

    public void setPessoasBean(PessoasBean pessoasBean) {
        this.pessoasBean = pessoasBean;
    }

    public EnderecosTO getEnderecosTO() {
        return enderecosTO;
    }

    public void setEnderecosTO(EnderecosTO enderecosTO) {
        this.enderecosTO = enderecosTO;
    }

    public EnderecosComplementosTO getEnderecosComplementosTO() {
        return enderecosComplementosTO;
    }

    public void setEnderecosComplementosTO(EnderecosComplementosTO enderecosComplementosTO) {
        this.enderecosComplementosTO = enderecosComplementosTO;
    }

    public List<EnderecosTO> getEnderecosTOs() {
        if (enderecosTOs == null) {
            enderecosTOs = new ArrayList<>();
        }
        return enderecosTOs;
    }

    public void setEnderecosTOs(List<EnderecosTO> enderecosTOs) {
        this.enderecosTOs = enderecosTOs;
    }

    public List<EnderecosComplementosTO> getEnderecosComplementosTOs() {
        if (enderecosComplementosTOs == null) {
            enderecosComplementosTOs = new ArrayList<>();
        }
        return enderecosComplementosTOs;
    }

    public void setEnderecosComplementosTOs(List<EnderecosComplementosTO> enderecosComplementosTOs) {
        this.enderecosComplementosTOs = enderecosComplementosTOs;
    }

    public List<EstadosTO> getEstadosTOs() {
        return estadosTOs;
    }

    public void setEstadosTOs(List<EstadosTO> estadosTOs) {
        this.estadosTOs = estadosTOs;
    }

    public List<CidadesTO> getCidadesTOs() {
        if (cidadesTOs == null) {
            cidadesTOs = new ArrayList<>();
        }
        return cidadesTOs;
    }

    public void setCidadesTOs(List<CidadesTO> cidadesTOs) {
        this.cidadesTOs = cidadesTOs;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(String idCidade) {
        this.idCidade = idCidade;
    }

}
