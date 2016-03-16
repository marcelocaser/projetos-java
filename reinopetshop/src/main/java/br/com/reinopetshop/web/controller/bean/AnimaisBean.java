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
import java.util.ArrayList;
import java.util.Date;
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
public class AnimaisBean extends ReinoPetController {

    @Autowired
    private EspeciesBO especiesNegocio;
    @Autowired
    private RacasBO racasNegocio;
    @Autowired
    private AnimaisBO animaisNegocio;
    @Autowired
    private PessoasBO pessoasNegocio;
    private AnimaisTO animaisTO;
    private AnimaisTO animaisSelected;
    private RacasTO racasTO;
    private List<AnimaisTO> animaisTOs;
    @Autowired
    private ClientesBean clientesBean;
    private List<EspeciesTO> especiesTOs;
    private List<RacasTO> racasTOs;
    private String idEspecies;
    private String idRacas;
    private String idAnimal;
    private List<String> selectedDadosComplementares;
    private String dataNascimentoNoFuturo;
    private String mostraBlocoDeAnimais;

    public static final String CADASTRADO = "C";
    public static final String OBITO = "O";
    public static final String A_VENDA = "V";
    public static final String CONTROLE_DE_RACAO = "R";

    /* Métodos para tratamento do negócio. */
    public String adicionarMaisAnimal() {
        try {
            if (animaisTO != null) {
                if (animaisTOs.stream().filter((p) -> p.getNome().equals(animaisTO.getNome())).collect(Collectors.toList()).size() > 0) {
                    setMessage("animaisJaCadastrado", EnumTipoMensagem.ATENCAO);
                    return "";
                }
                racasTO = racasNegocio.consultar(new RacasTO(new Integer(idRacas)));
                animaisTO.setIdRaca(racasTO);
                animaisTOs.add(animaisTO);
                setMessage("animaisAdicionado", EnumTipoMensagem.INFO);
                mostraBlocoDeAnimais = "block";
                animaisTO = new AnimaisTO();
                selectedDadosComplementares = new ArrayList<>();
                idEspecies = null;
                idRacas = null;
                idAnimal = null;
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String consultar() {
        try {
            if (idAnimal != null) {
                animaisSelected = animaisNegocio.consultar(new AnimaisTO(new Integer(idAnimal)));
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

    @PostConstruct
    public void listarEspecies() {
        this.validaDataNascimentoNoFuturo();
        especiesTOs = especiesNegocio.listar(new EspeciesTO());
    }

    public void listarAnimaisPorCliente() {
        if (clientesBean.getClientesTO() != null && clientesBean.getClientesTO().getPessoasTO().getNome() != null) {
            //Consulta cliente pelo nome
            PessoasTO pessoasTO = pessoasNegocio.consultar(clientesBean.getClientesTO().getPessoasTO());
            if (pessoasTO != null) {
                clientesBean.setClientesTO(pessoasTO.getClientesTO());
            }
        }
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
        animaisSelected = new AnimaisTO();
        animaisTOs = new ArrayList<>();
        selectedDadosComplementares = new ArrayList<>();
        idEspecies = null;
        idRacas = null;
        idAnimal = null;
        return "";
    }

    public String salvar() {
        try {
            for (AnimaisTO animais : animaisTOs) {
                this.validaDadosComplementares();
                animais.setIdCliente(clientesBean.getClientesTO());
                if (racasTO == null) {
                    racasTO = racasNegocio.consultar(new RacasTO(new Integer(idRacas)));
                }
                animais.setIdRaca(racasTO);
                animaisNegocio.incluir(animais);
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void validaDadosComplementares() {
        //Verifica as opções selecionadas dos dados complementares.
        for (String dadosComplementares : selectedDadosComplementares) {
            switch (dadosComplementares) {
                case CADASTRADO:
                    animaisTO.setCastrado('S');
                    break;
                case OBITO:
                    animaisTO.setObito('S');
                    break;
                case A_VENDA:
                    animaisTO.setAVenda('S');
                    break;
                case CONTROLE_DE_RACAO:
                    animaisTO.setControleDeRacao('S');
                    break;
                default:
                    break;
            }
        }
    }

    public void validaDataNascimentoNoFuturo() {
        Date dataAtual = pessoasNegocio.getDataAtual();
        if (dataAtual != null) {
            dataNascimentoNoFuturo = DateUtil.dataFormatter(dataAtual);
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

    public AnimaisTO getAnimaisTO() {
        return animaisTO;
    }

    public void setAnimaisTO(AnimaisTO animaisTO) {
        this.animaisTO = animaisTO;
    }

    public List<AnimaisTO> getAnimaisTOs() {
        return animaisTOs;
    }

    public void setAnimaisTOs(List<AnimaisTO> animaisTOs) {
        this.animaisTOs = animaisTOs;
    }

    public AnimaisTO getAnimaisSelected() {
        return animaisSelected;
    }

    public void setAnimaisSelected(AnimaisTO animaisSelected) {
        this.animaisSelected = animaisSelected;
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

    public String getMostraBlocoDeAnimais() {
        if (mostraBlocoDeAnimais == null) {
            mostraBlocoDeAnimais = "none";
        }
        return mostraBlocoDeAnimais;
    }

    public void setMostraBlocoDeAnimais(String mostraBlocoDeAnimais) {
        this.mostraBlocoDeAnimais = mostraBlocoDeAnimais;
    }

}
