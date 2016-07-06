package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.ServicosCategoriasTO;
import br.com.core.entity.ServicosTO;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.ServicosBO;
import br.com.reinopetshop.business.controller.business.ServicosCategoriasBO;
import java.util.List;
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
public class ServicosBean extends ReinoPetController {

    @Autowired
    private ServicosBO servicosNegocio;
    @Autowired
    private ServicosCategoriasBO servicosCategoriasNegocio;
    private ServicosTO servicosTO;
    private List<ServicosTO> servicosTOs;
    private String idServico;
    private String idServicoCategoria;
    private String precoVenda;
    private String valorACobrar;

    /* Métodos para tratamento do negócio. */
    public String consultar() {
        try {
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }
    
    public void consultarServicosCategorias() {
        ServicosCategoriasTO servicosCategoriasTO = servicosCategoriasNegocio.consultar(new ServicosCategoriasTO(new Integer(idServicoCategoria)));
        if (servicosCategoriasTO.getIdTabelaPreco() != null) {
            precoVenda = servicosCategoriasTO.getIdTabelaPreco().getPrecoVenda().toString();
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
    public void listarServicos() {
        servicosTOs = servicosNegocio.listar(new ServicosTO());
    }

    public void listarServicosCategorias() {
        if (idServico != null) {
            servicosTO = servicosNegocio.consultar(new ServicosTO(new Integer(idServico)));
            if (servicosTO.getIdTabelaPreco() != null) {
                precoVenda = servicosTO.getIdTabelaPreco().getPrecoVenda().toString();
            }
        }
    }

    public String novo() {
        servicosTO = new ServicosTO();
        idServico = null;
        idServicoCategoria = null;
        precoVenda = new String();
        valorACobrar = new String();
        return "";
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public ServicosBO getServicosNegocio() {
        return servicosNegocio;
    }

    public void setServicosNegocio(ServicosBO servicosNegocio) {
        this.servicosNegocio = servicosNegocio;
    }

    public ServicosCategoriasBO getServicosCategoriasNegocio() {
        return servicosCategoriasNegocio;
    }

    public void setServicosCategoriasNegocio(ServicosCategoriasBO servicosCategoriasNegocio) {
        this.servicosCategoriasNegocio = servicosCategoriasNegocio;
    }

    public ServicosTO getServicosTO() {
        return servicosTO;
    }

    public void setServicosTO(ServicosTO servicosTO) {
        this.servicosTO = servicosTO;
    }

    public List<ServicosTO> getServicosTOs() {
        return servicosTOs;
    }

    public void setServicosTOs(List<ServicosTO> servicosTOs) {
        this.servicosTOs = servicosTOs;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getIdServicoCategoria() {
        return idServicoCategoria;
    }

    public void setIdServicoCategoria(String idServicoCategoria) {
        this.idServicoCategoria = idServicoCategoria;
    }

    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getValorACobrar() {
        return valorACobrar;
    }

    public void setValorACobrar(String valorACobrar) {
        this.valorACobrar = valorACobrar;
    }

}
