package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.AnimaisTO;
import br.com.core.entity.EspeciesTO;
import br.com.core.entity.PessoasTO;
import br.com.core.entity.RacasTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.util.DateUtil;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.AnimaisBO;
import br.com.reinopetshop.business.controller.business.EspeciesBO;
import br.com.reinopetshop.business.controller.business.PessoasBO;
import br.com.reinopetshop.business.controller.business.RacasBO;
import br.com.reinopetshop.business.controller.persistence.interfaces.Animais;
import java.util.ArrayList;
import java.util.Date;
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
public class AnimaisBean extends ReinoPetController {

    @Autowired
    private EspeciesBO especiesNegocio;
    @Autowired
    private RacasBO racasNegocio;
    @Autowired
    private AnimaisBO animaisNegocio;
    @Autowired
    private PessoasBO pessoasNegocio;
    private ClientesBean clientesBean;
    private AnimaisTO animaisTO;
    private RacasTO racasTO;
    private List<AnimaisTO> animaisTOs;
    private List<EspeciesTO> especiesTOs;
    private List<RacasTO> racasTOs;
    private String idEspecies;
    private String idRacas;
    private String idAnimal;
    private List<String> selectedDadosComplementares;
    private String dataNascimentoNoFuturo;

    /* Métodos para tratamento do negócio. */
    public String adicionarMaisAnimal() {
        try {
            if (animaisTO != null) {
                if (animaisTOs.stream().filter((p) -> p.getNome().equals(animaisTO.getNome())).collect(Collectors.toList()).size() > 0) {
                    setMessage("animaisJaCadastrado", EnumTipoMensagem.ATENCAO);
                    return "";
                }
            }
            racasTO = racasNegocio.consultar(new RacasTO(new Integer(idRacas)));
            this.validaDadosComplementares(animaisTO);
            animaisTO.setIdRaca(racasTO);
            animaisTOs.add(animaisTO);
            setMessage("animaisAdicionado", EnumTipoMensagem.INFO);
            this.novo();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultar() {
        try {
            if (idAnimal != null) {
                animaisTO = animaisNegocio.consultar(new AnimaisTO(new Integer(idAnimal)));
            } else {
                animaisTO = animaisNegocio.consultar(animaisTO);
            }
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

    public String excluir() {
        try {
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            for (AnimaisTO animais : animaisTOs) {
                animais.setIdCliente(this.getClientesBean().getClientesTO());
                if (racasTO == null) {
                    racasTO = racasNegocio.consultar(new RacasTO(new Integer(idRacas)));
                    animais.setIdRaca(racasTO);
                }
                animaisNegocio.incluir(animais);
            }
            selectedDadosComplementares = null;
            animaisTOs = null;
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

    public void listarAnimaisPorCliente() {
        if (this.getClientesBean().getClientesTO() != null && this.getClientesBean().getClientesTO().getPessoasTO().getNome() != null) {
            //Consulta cliente pelo nome
            PessoasTO pessoasTO = pessoasNegocio.consultar(this.getClientesBean().getClientesTO().getPessoasTO());
            if (pessoasTO != null) {
                this.getClientesBean().setClientesTO(pessoasTO.getClientesTO());
            }
        }
    }

    public void listarEspecies() {
        especiesTOs = especiesNegocio.listar(new EspeciesTO());
    }

    public void listarRacas() {
        if (especiesTOs != null && !especiesTOs.isEmpty()) {
            RacasTO racasTO = new RacasTO();
            racasTO.setIdEspecie(new EspeciesTO(new Integer(idEspecies)));
            racasTOs = racasNegocio.listar(racasTO);
        } else {
            this.novo();
        }
    }

    public String novo() {
        animaisTO = new AnimaisTO();
        selectedDadosComplementares = null;
        idEspecies = null;
        idRacas = null;
        idAnimal = null;
        return "";
    }

    public void validaDadosComplementares(AnimaisTO animaisTO) {
        //Verifica as opções selecionadas dos dados complementares.
        for (String dadosComplementares : selectedDadosComplementares) {
            switch (dadosComplementares) {
                case Animais.CADASTRADO:
                    animaisTO.setCastrado('S');
                    break;
                case Animais.OBITO:
                    animaisTO.setObito('S');
                    break;
                case Animais.A_VENDA:
                    animaisTO.setAVenda('S');
                    break;
                case Animais.CONTROLE_DE_RACAO:
                    animaisTO.setControleDeRacao('S');
                    break;
                default:
                    break;
            }
            selectedDadosComplementares = null;
        }
    }

    public void validaDataNascimentoNoFuturo() {
        Date dataAtual = pessoasNegocio.getDataAtual();
        if (dataAtual != null) {
            dataNascimentoNoFuturo = DateUtil.dataFormatter(dataAtual);
        }
    }

    public String removerMaisAnimais() {
        try {
            animaisTOs.removeIf(p -> p.getNome().contains(animaisTO.getNome()));
            setMessage("animaisRemovido", EnumTipoMensagem.INFO);
            this.novo();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public EspeciesBO getEspeciesNegocio() {
        return especiesNegocio;
    }

    public void setEspeciesNegocio(EspeciesBO especiesNegocio) {
        this.especiesNegocio = especiesNegocio;
    }

    public RacasBO getRacasNegocio() {
        return racasNegocio;
    }

    public void setRacasNegocio(RacasBO racasNegocio) {
        this.racasNegocio = racasNegocio;
    }

    public AnimaisBO getAnimaisNegocio() {
        return animaisNegocio;
    }

    public void setAnimaisNegocio(AnimaisBO animaisNegocio) {
        this.animaisNegocio = animaisNegocio;
    }

    public PessoasBO getPessoasNegocio() {
        return pessoasNegocio;
    }

    public void setPessoasNegocio(PessoasBO pessoasNegocio) {
        this.pessoasNegocio = pessoasNegocio;
    }

    public ClientesBean getClientesBean() {
        if (clientesBean == null) {
            clientesBean = findBean("clientesBean");
        }
        return clientesBean;
    }

    public void setClientesBean(ClientesBean clientesBean) {
        this.clientesBean = clientesBean;
    }

    public AnimaisTO getAnimaisTO() {
        return animaisTO;
    }

    public void setAnimaisTO(AnimaisTO animaisTO) {
        this.animaisTO = animaisTO;
    }

    public List<AnimaisTO> getAnimaisTOs() {
        if (animaisTOs == null) {
            animaisTOs = new ArrayList<>();
        }
        return animaisTOs;
    }

    public void setAnimaisTOs(List<AnimaisTO> animaisTOs) {
        this.animaisTOs = animaisTOs;
    }

    public RacasTO getRacasTO() {
        return racasTO;
    }

    public void setRacasTO(RacasTO racasTO) {
        this.racasTO = racasTO;
    }

    public List<EspeciesTO> getEspeciesTOs() {
        return especiesTOs;
    }

    public void setEspeciesTOs(List<EspeciesTO> especiesTOs) {
        this.especiesTOs = especiesTOs;
    }

    public List<RacasTO> getRacasTOs() {
        return racasTOs;
    }

    public void setRacasTOs(List<RacasTO> racasTOs) {
        this.racasTOs = racasTOs;
    }

    public String getIdEspecies() {
        return idEspecies;
    }

    public void setIdEspecies(String idEspecies) {
        this.idEspecies = idEspecies;
    }

    public String getIdRacas() {
        return idRacas;
    }

    public void setIdRacas(String idRacas) {
        this.idRacas = idRacas;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public List<String> getSelectedDadosComplementares() {
        if (selectedDadosComplementares == null) {
            selectedDadosComplementares = new ArrayList<>();
        }
        return selectedDadosComplementares;
    }

    public void setSelectedDadosComplementares(List<String> selectedDadosComplementares) {
        this.selectedDadosComplementares = selectedDadosComplementares;
    }

    public String getDataNascimentoNoFuturo() {
        return dataNascimentoNoFuturo;
    }

    public void setDataNascimentoNoFuturo(String dataNascimentoNoFuturo) {
        this.dataNascimentoNoFuturo = dataNascimentoNoFuturo;
    }

}
