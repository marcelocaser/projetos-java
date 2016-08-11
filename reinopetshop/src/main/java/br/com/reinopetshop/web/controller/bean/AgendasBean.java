package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.AgendasTO;
import br.com.core.entity.AnimaisTO;
import br.com.core.entity.ServicosCategoriasTO;
import br.com.core.entity.ServicosTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.util.DateUtil;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.AgendasBO;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class AgendasBean extends ReinoPetController {

    @Autowired
    private AgendasBO agendasNegocio;

    private ClientesBean clientesBean;
    private AnimaisBean animaisBean;
    private ServicosBean servicosBean;
    private PessoasBean pessoasBean;
    private ScheduleEvent scheduleEvent;
    private ScheduleModel eventModel;
    private AgendasTO agendasTO;
    private List<AgendasTO> agendasTOs;
    private List<AgendasTO> agendaDoMes;
    private List<AgendasTO> agendaDoDia;
    private String repetirEvento;
    private Date dataEhora;
    private int diaDaSemana;
    private Boolean mostraBotaoExcluir;
    private Boolean editarEventoAtual;
    private Boolean validarHorario;
    private Boolean semanaSeguinte;

    public AgendasBean() {
        eventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                //Buscar todos os eventos cadastrados e alimentar calendario
                listarAgendamentos();
            }
        };
    }

    /* Metodos para tratamento do negocio. */
    public void adicionaEventoCalendario() {
        DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();
        this.montaDataEHora();
        defaultScheduleEvent.setDescription(agendasTO.getObservacao());
        defaultScheduleEvent.setStartDate(dataEhora);
        defaultScheduleEvent.setEndDate(dataEhora);
        //defaultScheduleEvent.setTitle(animaisBean.getAnimaisSelected().getNome());
        defaultScheduleEvent.setData(agendasTO);
        scheduleEvent = defaultScheduleEvent;
        eventModel.addEvent(scheduleEvent);
        if (repetirEvento == null) {
            scheduleEvent = new DefaultScheduleEvent();
        }
    }

    public void atualizaEventoCalendario() {
        DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();
        this.montaDataEHora();
        defaultScheduleEvent.setDescription(agendasTO.getObservacao());
        defaultScheduleEvent.setStartDate(dataEhora);
        defaultScheduleEvent.setEndDate(dataEhora);
        //defaultScheduleEvent.setTitle(animaisBean.getAnimaisSelected().getNome());
        defaultScheduleEvent.setData(agendasTO);
        scheduleEvent = defaultScheduleEvent;
        eventModel.updateEvent(scheduleEvent);
        this.listarAgendamentosDoMes();
        this.listarCompromissosDoDia();
    }

    public void confirmarHorario() {
        validarHorario = Boolean.FALSE;
        if (semanaSeguinte) {
            this.incluirSemanaSeguinte();
        } else {
            this.incluir();
        }
    }

    public String consultar() {
        try {
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
            if (agendasTO != null) {
                agendasNegocio.excluir(agendasTO);
            }
            setMessage("agendasExcluidoComSucesso", EnumTipoMensagem.INFO);
            this.novo();
            this.listarAgendamentosDoMes();
            this.listarCompromissosDoDia();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            agendasTO.setData(scheduleEvent.getEndDate());
            agendasTO.setHora(scheduleEvent.getStartDate());
            if (isHorarioValido()) {
                agendasTO.setValor(new BigDecimal(!servicosBean.getPrecoVenda().isEmpty() ? servicosBean.getPrecoVenda() : "0"));
                agendasTO.setValorACobrar(new BigDecimal(!servicosBean.getValorACobrar().isEmpty() ? servicosBean.getValorACobrar() : "0"));
                agendasTO.setIraRepetirEm(!repetirEvento.isEmpty() ? new Integer(repetirEvento) : null);
                agendasTO.setIdCliente(clientesBean.getClientesTO());
                agendasTO.setIdAnimal(animaisBean.getAnimaisNegocio().consultar(new AnimaisTO(new Integer(animaisBean.getIdAnimal()))));
                agendasTO.setIdServico(servicosBean.getServicosNegocio().consultar(new ServicosTO(new Integer(servicosBean.getIdServico()))));
                agendasTO.setIdServicoCategoria(servicosBean.getIdServicoCategoria().isEmpty() ? null : servicosBean.getServicosCategoriasNegocio().consultar(new ServicosCategoriasTO(new Integer(servicosBean.getIdServicoCategoria()))));
                if (editarEventoAtual) {
                    agendasNegocio.alterar(agendasTO);
                    eventModel.updateEvent(scheduleEvent);
                } else {
                    agendasNegocio.incluir(agendasTO);
                    this.adicionaEventoCalendario();
                }
                if (!repetirEvento.isEmpty() && !editarEventoAtual) {
                    this.incluirSemanaSeguinte();
                }
                setMessage("agendasCadastraComSucesso", EnumTipoMensagem.INFO);
                this.novo();
                this.listarAgendamentosDoMes();
                this.listarCompromissosDoDia();
                primefacesAddParamCallback("validarHorario", Boolean.TRUE);
                primefacesUpdate("agenda");
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    private String incluirSemanaSeguinte() {
        semanaSeguinte = Boolean.TRUE;
        //validarHorario = getConfiguracaoDoSistema().getValidaHorario();
        try {
            Date dataProxima = scheduleEvent.getEndDate();
            for (; diaDaSemana <= new Integer(repetirEvento);) {
                agendasTO = new AgendasTO();
                agendasTO.setData(DateUtil.adicionaDiaMesAno(dataProxima, Calendar.WEEK_OF_MONTH, diaDaSemana, Boolean.TRUE));
                agendasTO.setHora(scheduleEvent.getStartDate());
                if (isHorarioValido()) {
                    agendasTO.setIdCliente(clientesBean.getClientesTO());
                    agendasTO.setIdAnimal(animaisBean.getAnimaisTO());
                    agendasTO.setIdServico(servicosBean.getServicosTO());
                    agendasTO.setIdServicoCategoria(servicosBean.getIdServicoCategoria().isEmpty() ? null : servicosBean.getServicosCategoriasNegocio().consultar(new ServicosCategoriasTO(new Integer(servicosBean.getIdServicoCategoria()))));
                    agendasNegocio.incluir(agendasTO);
                    diaDaSemana++;
                    validarHorario = getConfiguracaoDoSistema().getValidaHorario();
                    if (agendasTO.getId() != null) {
                        this.adicionaEventoCalendario();
                    }
                } else {
                    return "";
                }
            }
            scheduleEvent = new DefaultScheduleEvent();
        } catch (Exception ex) {
            tratarExcecao(ex);
        }
        return "";
    }

    private boolean isHorarioValido() {
        if (!validarHorario) {
            return Boolean.TRUE;
        }
        agendasTOs = agendasNegocio.listarBanhoPorDataHora(agendasTO.getData(), agendasTO.getHora());
        if (agendasTOs.size() >= getConfiguracaoDoSistema().getMaximoAgendamentosMesmaHora()) {
            primefacesExecute("PF('confirmacaoMesmoHorario').show()");
            primefacesAddParamCallback("validarHorario", Boolean.FALSE);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String listar() {
        try {
            //TODO valida, pois deixa mais lento a abertura do agendamento. Executado toda vez que abre o calendario
            agendasTOs = agendasNegocio.listar();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void listarAgendamentos() {
        this.listar();
        for (AgendasTO agendas : agendasTOs) {
            agendasTO = agendas;
            this.montaDataEHora();
            eventModel.addEvent(new DefaultScheduleEvent(agendas.getIdAnimal().getNome(), dataEhora, dataEhora, agendas));
        }
    }

    public void listarAgendamentosDoMes() {
        //Buscar todos os eventos cadastrados e alimentar calendario
        Date dataAtual = getPessoasBean().getPessoasNegocio().getDataAtual();
        Date dataInicial = DateUtil.getPrimeiroDiaMes(dataAtual);
        Date dataFinal = DateUtil.getUltimoDiaMes(dataAtual);
        agendaDoMes = agendasNegocio.listarPorPeriodo(dataInicial, dataFinal);
        this.listarAgendamentos();
//        for (AgendasTO agendas : agendaDoMes) {
//            agendasTO = agendas;
//            this.montaDataEHora();
//            eventModel.addEvent(new DefaultScheduleEvent(agendas.getIdAnimal().getNome(), dataEhora, dataEhora, agendas));
//        }
    }

    public void listarCompromissosDoDia() {
        Date dataAtual = getPessoasBean().getPessoasNegocio().getDataAtual();
        agendaDoDia = agendasNegocio.listarPorPeriodo(dataAtual, dataAtual, getConfiguracaoDoSistema().getMaximoAgendamentosPainel());
    }

    public void montaDataEHora() {
        dataEhora = agendasNegocio.dataEHora(agendasTO.getData(), agendasTO.getHora());
    }

    public String novo() {
        agendasTO = new AgendasTO();
        getClientesBean().novo();
        getAnimaisBean().novo();
        getServicosBean().novo();
        repetirEvento = null;
        mostraBotaoExcluir = Boolean.FALSE;
        editarEventoAtual = Boolean.FALSE;
        validarHorario = getConfiguracaoDoSistema().getValidaHorario();
        semanaSeguinte = Boolean.FALSE;
        diaDaSemana = BigDecimal.ONE.intValue();
        return "/app/agendas/agendasNovo";
    }

    /* Metodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public ClientesBean getClientesBean() {
        if (clientesBean == null) {
            clientesBean = findBean("clientesBean");
        }
        return clientesBean;
    }

    public void setClientesBean(ClientesBean clientesBean) {
        this.clientesBean = clientesBean;
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

    public ServicosBean getServicosBean() {
        if (servicosBean == null) {
            servicosBean = findBean("servicosBean");
        }
        return servicosBean;
    }

    public void setServicosBean(ServicosBean servicosBean) {
        this.servicosBean = servicosBean;
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

    public ScheduleEvent getScheduleEvent() {
        if (scheduleEvent == null) {
            scheduleEvent = new DefaultScheduleEvent();
        }
        return scheduleEvent;
    }

    public void setScheduleEvent(ScheduleEvent scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }

    public ScheduleModel getEventModel() {
        if (eventModel == null) {
            eventModel = new DefaultScheduleModel();
        }
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public AgendasBO getAgendasNegocio() {
        return agendasNegocio;
    }

    public void setAgendasNegocio(AgendasBO agendasNegocio) {
        this.agendasNegocio = agendasNegocio;
    }

    public AgendasTO getAgendasTO() {
        return agendasTO;
    }

    public void setAgendasTO(AgendasTO agendasTO) {
        this.agendasTO = agendasTO;
    }

    public List<AgendasTO> getAgendasTOs() {
        return agendasTOs;
    }

    public void setAgendasTOs(List<AgendasTO> agendasTOs) {
        this.agendasTOs = agendasTOs;
    }

    public List<AgendasTO> getAgendaDoMes() {
        return agendaDoMes;
    }

    public void setAgendaDoMes(List<AgendasTO> agendaDoMes) {
        this.agendaDoMes = agendaDoMes;
    }

    public List<AgendasTO> getAgendaDoDia() {
        return agendaDoDia;
    }

    public void setAgendaDoDia(List<AgendasTO> agendaDoDia) {
        this.agendaDoDia = agendaDoDia;
    }

    public String getRepetirEvento() {
        return repetirEvento;
    }

    public void setRepetirEvento(String repetirEvento) {
        this.repetirEvento = repetirEvento;
    }

    public Date getDataEhora() {
        return dataEhora;
    }

    public void setDataEhora(Date dataEhora) {
        this.dataEhora = dataEhora;
    }

    public int getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(int diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Boolean getMostraBotaoExcluir() {
        return mostraBotaoExcluir;
    }

    public void setMostraBotaoExcluir(Boolean mostraBotaoExcluir) {
        this.mostraBotaoExcluir = mostraBotaoExcluir;
    }

    public Boolean getEditarEventoAtual() {
        return editarEventoAtual;
    }

    public void setEditarEventoAtual(Boolean editarEventoAtual) {
        this.editarEventoAtual = editarEventoAtual;
    }

    public Boolean getValidarHorario() {
        return validarHorario;
    }

    public void setValidarHorario(Boolean validarHorario) {
        this.validarHorario = validarHorario;
    }

    public Boolean getSemanaSeguinte() {
        return semanaSeguinte;
    }

    public void setSemanaSeguinte(Boolean semanaSeguinte) {
        this.semanaSeguinte = semanaSeguinte;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        scheduleEvent = (ScheduleEvent) selectEvent.getObject();
        //this.novo();
        mostraBotaoExcluir = Boolean.TRUE;
        editarEventoAtual = Boolean.TRUE;
        agendasTO = (AgendasTO) scheduleEvent.getData();
        repetirEvento = String.valueOf(agendasTO.getIraRepetirEm() != null ? agendasTO.getIraRepetirEm() : "");
        clientesBean.setClientesTO(agendasTO.getIdCliente());
        animaisBean.setIdAnimal(String.valueOf(agendasTO.getIdAnimal().getId() != null ? agendasTO.getIdAnimal().getId() : ""));
        servicosBean.setIdServico(String.valueOf(agendasTO.getIdServico().getId() != null ? agendasTO.getIdServico().getId() : ""));
        servicosBean.setIdServicoCategoria(agendasTO.getIdServicoCategoria() != null ? String.valueOf(agendasTO.getIdServicoCategoria().getId() != null ? agendasTO.getIdServicoCategoria().getId() : "") : "");
        servicosBean.setIdServicoGrupo(String.valueOf(agendasTO.getIdServico().getIdServicoGrupo().getId() != null ? agendasTO.getIdServico().getIdServicoGrupo().getId() : ""));
        servicosBean.listarServicos();
        servicosBean.listarServicosCategorias();
        servicosBean.setPrecoVenda(String.valueOf(agendasTO.getValor() != null ? agendasTO.getValor() : ""));
        servicosBean.setValorACobrar(String.valueOf(agendasTO.getValorACobrar() != null ? agendasTO.getValorACobrar() : ""));
    }

    public void onDateSelect(SelectEvent selectEvent) {
        try {
            scheduleEvent = new DefaultScheduleEvent("", DateUtil.getHora("07:00:00", DateUtil.HORA), (Date) selectEvent.getObject());
        } catch (Exception ex) {
            tratarExcecao(ex);
        }
        this.novo();
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        scheduleEvent = (ScheduleEvent) event.getScheduleEvent();
        agendasTO = (AgendasTO) scheduleEvent.getData();
        Date dataProxima = agendasTO.getData();
        agendasTO.setData(DateUtil.adicionaDiaMesAno(dataProxima, Calendar.DAY_OF_MONTH, event.getDayDelta(), Boolean.TRUE));
        agendasNegocio.alterar(agendasTO);
        this.atualizaEventoCalendario();
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        scheduleEvent = (ScheduleEvent) event.getScheduleEvent();
    }

}
