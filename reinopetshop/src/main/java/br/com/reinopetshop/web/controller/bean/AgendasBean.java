package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.AgendasTO;
import br.com.core.entity.AnimaisTO;
import br.com.core.entity.ServicosTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.util.DateUtil;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.AgendasBO;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
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

    private ScheduleEvent scheduleEvent;
    private ScheduleModel eventModel;
    @Autowired
    private AgendasBO agendasNegocio;
    private AgendasTO agendasTO;
    private List<AgendasTO> agendasTOs;
    private List<AgendasTO> agendaDoDia;
    @Autowired
    private ClientesBean clientesBean;
    @Autowired
    private AnimaisBean animaisBean;
    @Autowired
    private ServicosBean servicosBean;
    @Autowired
    private PessoasBean pessoasBean;
    private String repetirEvento;
    private Date dataEhora;
    private Boolean mostraBotaoExcluir;
    private Boolean editarEventoAtual;

    public AgendasBean() {
        eventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                //Buscar todos os eventos cadastrados e alimentar calendário
                buscarAgendamentos();
            }
        };
    }

    /* Métodos para tratamento do negócio. */
    public void adicionaEventoCalendario() {
        DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();
        this.montaDataEHora();
        defaultScheduleEvent.setDescription(agendasTO.getObservacao());
        defaultScheduleEvent.setStartDate(dataEhora);
        defaultScheduleEvent.setEndDate(dataEhora);
        defaultScheduleEvent.setTitle(animaisBean.getAnimaisSelected().getNome());
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
        defaultScheduleEvent.setTitle(animaisBean.getAnimaisSelected().getNome());
        defaultScheduleEvent.setData(agendasTO);
        scheduleEvent = defaultScheduleEvent;
        eventModel.updateEvent(scheduleEvent);
    }

    public void buscarAgendamentos() {
        this.listar();
        for (AgendasTO agendas : agendasTOs) {
            agendasTO = agendas;
            this.montaDataEHora();
            eventModel.addEvent(new DefaultScheduleEvent(agendas.getIdAnimal().getNome(), dataEhora, dataEhora, agendas));
        }
    }

    public void buscarAgendamentosDoMes() {
        //Buscar todos os eventos cadastrados e alimentar calendário
        Date dataAtual = pessoasBean.getPessoasNegocio().getDataAtual();
        Date dataInicial = DateUtil.getPrimeiroDiaMes(dataAtual);
        Date dataFinal = DateUtil.getUltimoDiaMes(dataAtual);
        agendasTOs = agendasNegocio.listarPorPeriodo(dataInicial, dataFinal);
        for (AgendasTO agendas : agendasTOs) {
            agendasTO = agendas;
            this.montaDataEHora();
            eventModel.addEvent(new DefaultScheduleEvent(agendas.getIdAnimal().getNome(), dataEhora, dataEhora, agendas));
        }
    }

    public String consultar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void montaDataEHora() {
        dataEhora = agendasNegocio.dataEHora(agendasTO.getData(), agendasTO.getHora());
    }

    public String editar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String excluir(ActionEvent actionEvent) {
        try {
            if (agendasTO != null) {
                agendasNegocio.excluir(agendasTO);
            }
            return "";
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

    @PostConstruct
    public void init() {
        this.buscarAgendamentosDoMes();
        this.listarCompromissosDoDia();
    }

    public String listar() {
        try {
            agendasTOs = agendasNegocio.listar();
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void listarUltimosCompromissos() {
    }
    
    public void listarCompromissosDoDia() {
        Date dataAtual = pessoasBean.getPessoasNegocio().getDataAtual();
        agendaDoDia = agendasNegocio.listarPorPeriodo(dataAtual, dataAtual);
    }

    public String novo() {
        agendasTO = new AgendasTO();
        clientesBean.novo();
        animaisBean.novo();
        servicosBean.novo();
        repetirEvento = null;
        mostraBotaoExcluir = false;
        editarEventoAtual = false;
        return "/app/agendas/agendasNovo";
    }

    public void salvar(ActionEvent actionEvent) {
        this.salvar();
        this.listarCompromissosDoDia();
    }

    public String salvar() {
        try {
            agendasTO.setData(scheduleEvent.getEndDate());
            agendasTO.setHora(scheduleEvent.getStartDate());
            agendasTO.setValor(new BigDecimal(!servicosBean.getPrecoVenda().isEmpty() ? servicosBean.getPrecoVenda() : "0"));
            agendasTO.setValorACobrar(new BigDecimal(!servicosBean.getValorACobrar().isEmpty() ? servicosBean.getValorACobrar() : "0"));
            agendasTO.setIraRepetirEm(!repetirEvento.isEmpty() ? new Integer(repetirEvento) : null);
            agendasTO.setIdCliente(clientesBean.getClientesTO());
            agendasTO.setIdAnimal(animaisBean.getAnimaisNegocio().consultar(new AnimaisTO(new Integer(animaisBean.getIdAnimal()))));
            agendasTO.setIdServico(servicosBean.getServicosNegocio().consultar(new ServicosTO(new Integer(servicosBean.getIdServico()))));
            if (editarEventoAtual) {
                agendasNegocio.alterar(agendasTO);
                eventModel.updateEvent(scheduleEvent);
            } else {
                agendasNegocio.incluir(agendasTO);
                this.adicionaEventoCalendario();
            }
            if (!repetirEvento.isEmpty() && !editarEventoAtual) {
                Date dataProxima = scheduleEvent.getEndDate();
                for (int i = 1; i <= new Integer(repetirEvento); i++) {
                    agendasTO = new AgendasTO();
                    agendasTO.setData(DateUtil.adicionaDiaMesAno(dataProxima, Calendar.MONTH, i, true));
                    agendasTO.setHora(DateUtil.getHora("07:00:00", DateUtil.HORA));
                    agendasTO.setIdCliente(clientesBean.getClientesTO());
                    agendasTO.setIdAnimal(animaisBean.getAnimaisSelected());
                    agendasTO.setIdServico(servicosBean.getServicosTO());
                    agendasNegocio.incluir(agendasTO);
                    if (agendasTO.getId() != null) {
                        this.adicionaEventoCalendario();
                    }
                }
                scheduleEvent = new DefaultScheduleEvent();
            }
            setMessage("agendasCadastraComSucesso", EnumTipoMensagem.INFO);
            return "";
        } catch (Exception ex) {
            return tratarExcecao(ex);
        }
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
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

    public void onEventSelect(SelectEvent selectEvent) {
        scheduleEvent = (ScheduleEvent) selectEvent.getObject();
        this.novo();
        mostraBotaoExcluir = true;
        editarEventoAtual = true;
        agendasTO = (AgendasTO) scheduleEvent.getData();
        repetirEvento = String.valueOf(agendasTO.getIraRepetirEm() != null ? agendasTO.getIraRepetirEm() : "");
        clientesBean.setClientesTO(agendasTO.getIdCliente());
        animaisBean.setIdAnimal(String.valueOf(agendasTO.getIdAnimal().getId() != null ? agendasTO.getIdAnimal().getId() : ""));
        servicosBean.setIdServico(String.valueOf(agendasTO.getIdServico().getId() != null ? agendasTO.getIdServico().getId() : ""));
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
        agendasTO.setData(DateUtil.adicionaDiaMesAno(dataProxima, Calendar.DAY_OF_MONTH, event.getDayDelta(), true));
        agendasNegocio.alterar(agendasTO);
        this.atualizaEventoCalendario();
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        scheduleEvent = (ScheduleEvent) event.getScheduleEvent();
    }

}
