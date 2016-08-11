package br.com.reinopetshop.web.controller.bean;

import br.com.core.controller.business.SistemasConfiguracoesBO;
import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.reinopetshop.business.controller.ReinoPetController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class SistemasConfiguracoesBean extends ReinoPetController {

    @Autowired
    private SistemasConfiguracoesBO sistemasConfiguracoesNegocio;
    private AgendasBean agendasBean;
    private ClientesBean clientesBean;
    private AnimaisBean animaisBean;
    private EnderecosBean enderecosBean;
    private ServicosBean servicosBean;
    private SistemasConfiguracoesTO sistemasConfiguracoesTO;
    private Integer totalCarregado;

    /* Metodos para tratamento do negocio. */
    public void buscarConfiguracoesDoSistema() {
        //Busca as configuracoes do sistema de acordo com os parametros informados
        sistemasConfiguracoesTO = sistemasConfiguracoesNegocio.consultar(new SistemasConfiguracoesTO(new Integer("1")));
    }

    public void executarConfiguracoesDoSistema() {
        if (sistemasConfiguracoesTO != null) {
            sistemasConfiguracoesTO.getClass().getDeclaredFields();
            //agenda.
            this.getAgendasBean().listarAgendamentosDoMes();
            this.getAgendasBean().listarCompromissosDoDia();
            //cliente.
            this.getClientesBean().listar();
            this.getClientesBean().listarUltimosClientes();
            //animais
            this.getAnimaisBean().validaDataNascimentoNoFuturo();
            this.getAnimaisBean().listarEspecies();
            //endereco
            this.getEnderecosBean().listarEstados();
            //servico
            this.getServicosBean().listarServicosGrupos();
        }
    }

    public void finalizarConfiguracoesDoSistema() {
        setMessage("configuracoesCarregadasComSucesso", EnumTipoMensagem.INFO);
    }

    public void cancel() {
        totalCarregado = null;
    }

    /* Metodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public AgendasBean getAgendasBean() {
        if (agendasBean == null) {
            agendasBean = findBean("agendasBean");
        }
        return agendasBean;
    }

    public void setAgendasBean(AgendasBean agendasBean) {
        this.agendasBean = agendasBean;
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

    public AnimaisBean getAnimaisBean() {
        if (animaisBean == null) {
            animaisBean = findBean("animaisBean");
        }
        return animaisBean;
    }

    public void setAnimaisBean(AnimaisBean animaisBean) {
        this.animaisBean = animaisBean;
    }

    public EnderecosBean getEnderecosBean() {
        if (enderecosBean == null) {
            enderecosBean = findBean("enderecosBean");
        }
        return enderecosBean;
    }

    public void setEnderecosBean(EnderecosBean enderecosBean) {
        this.enderecosBean = enderecosBean;
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
    
    public SistemasConfiguracoesTO getSistemasConfiguracoesTO() {
        return sistemasConfiguracoesTO;
    }

    public void setSistemasConfiguracoesTO(SistemasConfiguracoesTO sistemasConfiguracoesTO) {
        this.sistemasConfiguracoesTO = sistemasConfiguracoesTO;
    }

    public Integer getTotalCarregado() {
        if (totalCarregado == null) {
            totalCarregado = 0;
        } else {
            totalCarregado = totalCarregado + (int) (Math.random() * 35);

            if (totalCarregado > 100) {
                totalCarregado = 100;
            }
        }
        return totalCarregado;
    }

    public void setTotalCarregado(Integer totalCarregado) {
        this.totalCarregado = totalCarregado;
    }

}
