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
import javax.annotation.PostConstruct;
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
    @Autowired
    private PessoasBean pessoasBean;
    private EnderecosTO enderecosTO;
    private EnderecosTO enderecosSelected;
    private EnderecosComplementosTO enderecosComplementosTO;
    private List<EstadosTO> estadosTOs;
    private List<CidadesTO> cidadesTOs;
    private List<EnderecosTO> enderecosTOs;
    private String idEstado;
    private String idCidade;
    private String mostraBlocoDeEnderecos;

    private static final String URL_WS_CEP = "http://localhost:8084/cep/ws/consultar.json?cep=[cep]&chave=[chave]";

    /* Métodos para tratamento do negócio. */
    public String adicionarMaisEndereco() {
        try {
            if (enderecosTO != null) {
                if (enderecosTOs.stream().filter((p) -> p.getCep().equals(enderecosTO.getCep())).collect(Collectors.toList()).size() > 0) {
                    setMessage("enderecosJaCadastrado", EnumTipoMensagem.ATENCAO);
                    return "";
                }
            }
            enderecosTO.getEnderecosComplementosTOList().add(enderecosComplementosTO);
            enderecosTOs.add(enderecosTO);
            setMessage("enderecosAdicionado", EnumTipoMensagem.INFO);
            mostraBlocoDeEnderecos = "block";
            enderecosTO = new EnderecosTO();
            enderecosTO.setIdBairro(new BairrosTO());
            enderecosTO.setIdCidade(new CidadesTO());
            enderecosComplementosTO = new EnderecosComplementosTO();
            idCidade = null;
            idEstado = null;
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
            if (enderecosTO != null) {
                enderecosTO.setCep(CEPUtil.isCEPValido(enderecosTO.getCep()));
                if (enderecosTO.getCep() != null) {
                    RetornoCEP retornoCEP = new RetornoCEP();
                    retornoCEP = (RetornoCEP) RestUtil.buscaRetornoRestSpring(retornoCEP, URL_WS_CEP.replace("[cep]", enderecosTO.getCep()).replace("[chave]", getUsuarioLogado().getChave()));
                    if (retornoCEP != null && !retornoCEP.getStatus().equals(CEPUtil.ERRO)) {
                        enderecosTO = enderecosNegocio.consultar(new EnderecosTO(retornoCEP.getIdEndereco()));
                        if (enderecosTO != null && enderecosTO.getIdCidade() != null && enderecosTO.getIdCidade().getIdEstado() != null) {
                            idEstado = enderecosTO.getIdCidade().getIdEstado().getId().toString();
                            this.listarCidades();
                            idCidade = enderecosTO.getIdCidade().getId().toString();
                        } else {
                            idEstado = null;
                            idCidade = null;
                            this.novo();
                            setMessage("enderecosErroConsultarCep", EnumTipoMensagem.ERRO);
                        }
                    } else {
                        idEstado = null;
                        idCidade = null;
                        this.novo();
                        setMessage("enderecosCepNaoExiste", EnumTipoMensagem.INFO);
                    }
                }
            } else {
                setMessage("enderecosCepInvalido", EnumTipoMensagem.ERRO);
            }
            return null;
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

    public String removerMaisEndereco() {
        try {
            enderecosTOs.removeIf(p -> p.getCep().contains(enderecosSelected.getCep()));
            setMessage("enderecosRemovido", EnumTipoMensagem.INFO);
            if (enderecosTOs.isEmpty()) {
                mostraBlocoDeEnderecos = null;
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    @PostConstruct
    public void listarEstados() {
        estadosTOs = estadosNegocio.listar(new EstadosTO());
    }

    public String novo() {
        enderecosTO = new EnderecosTO();
        enderecosTO.setIdBairro(new BairrosTO());
        enderecosTO.setIdCidade(new CidadesTO());
        enderecosTO.setEnderecosComplementosTOList(new ArrayList<>());
        enderecosTO.setPessoasTOList(new ArrayList<>());
        enderecosComplementosTO = new EnderecosComplementosTO();
        mostraBlocoDeEnderecos = null;
        enderecosSelected = null;
        enderecosTOs = null;
        idCidade = null;
        idEstado = null;
        return null;
    }

    public String salvar() {
        try {
            for (EnderecosTO end : enderecosTOs) {
                enderecosComplementosTO.setIdEndereco(end);
                enderecosComplementosTO.setCep(end.getCep());
                for (EnderecosComplementosTO complementosTO : end.getEnderecosComplementosTOList()) {
                     enderecosComplementosTO.setComplemento(complementosTO.getComplemento());
                     enderecosComplementosTO.setNumero(complementosTO.getNumero());
                     enderecosComplementosTO.setObservacao(complementosTO.getObservacao());
                     complementosNegocio.incluir(enderecosComplementosTO);
                }
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public EnderecosBO getEnderecosNegocio() {
        return enderecosNegocio;
    }

    public void setEnderecosNegocio(EnderecosBO enderecosNegocio) {
        this.enderecosNegocio = enderecosNegocio;
    }

    public EstadosBO getEstadosNegocio() {
        return estadosNegocio;
    }

    public void setEstadosNegocio(EstadosBO estadosNegocio) {
        this.estadosNegocio = estadosNegocio;
    }

    public CidadesBO getCidadesNegocio() {
        return cidadesNegocio;
    }

    public void setCidadesNegocio(CidadesBO cidadesNegocio) {
        this.cidadesNegocio = cidadesNegocio;
    }

    public EnderecosComplementosBO getComplementosNegocio() {
        return complementosNegocio;
    }

    public void setComplementosNegocio(EnderecosComplementosBO complementosNegocio) {
        this.complementosNegocio = complementosNegocio;
    }

    public EnderecosTO getEnderecosTO() {
        return enderecosTO;
    }

    public void setEnderecosTO(EnderecosTO enderecosTO) {
        this.enderecosTO = enderecosTO;
    }

    public EnderecosTO getEnderecosSelected() {
        return enderecosSelected;
    }

    public void setEnderecosSelected(EnderecosTO enderecosSelected) {
        this.enderecosSelected = enderecosSelected;
    }

    public EnderecosComplementosTO getEnderecosComplementosTO() {
        return enderecosComplementosTO;
    }

    public void setEnderecosComplementosTO(EnderecosComplementosTO enderecosComplementosTO) {
        this.enderecosComplementosTO = enderecosComplementosTO;
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

    public List<EnderecosTO> getEnderecosTOs() {
        if (enderecosTOs == null) {
            enderecosTOs = new ArrayList<>();
        }
        return enderecosTOs;
    }

    public void setEnderecosTOs(List<EnderecosTO> enderecosTOs) {
        this.enderecosTOs = enderecosTOs;
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

    public String getMostraBlocoDeEnderecos() {
        if (mostraBlocoDeEnderecos == null) {
            mostraBlocoDeEnderecos = "none";
        }
        return mostraBlocoDeEnderecos;
    }

    public void setMostraBlocoDeEnderecos(String mostraBlocoDeEnderecos) {
        this.mostraBlocoDeEnderecos = mostraBlocoDeEnderecos;
    }

}
