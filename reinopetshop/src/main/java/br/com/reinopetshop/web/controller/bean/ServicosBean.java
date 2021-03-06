package br.com.reinopetshop.web.controller.bean;

import br.com.core.entity.ServicosCategoriasTO;
import br.com.core.entity.ServicosGruposTO;
import br.com.core.entity.ServicosTO;
import br.com.reinopetshop.business.controller.ReinoPetController;
import br.com.reinopetshop.business.controller.business.ServicosBO;
import br.com.reinopetshop.business.controller.business.ServicosCategoriasBO;
import br.com.reinopetshop.business.controller.business.ServicosGruposBO;
import java.util.List;
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
    @Autowired
    private ServicosGruposBO servicosGruposNegocio;
    private ServicosTO servicosTO;
    private ServicosCategoriasTO servicosCategoriasTO;
    private List<ServicosTO> servicosTOs;
    private List<ServicosGruposTO> servicosGruposTOs;
    private String idServico;
    private String idServicoCategoria;
    private String idServicoGrupo;
    private String precoVenda;
    private String valorACobrar;

    /* Métodos para tratamento do negócio. */
    public String consultar() {
        try {
            return "/app/servicos/servicosConsultar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void consultarServicosCategorias() {
        if (idServicoCategoria != null && !idServicoCategoria.isEmpty()) {
            servicosCategoriasTO = servicosCategoriasNegocio.consultar(new ServicosCategoriasTO(new Integer(idServicoCategoria)));
            if (servicosCategoriasTO.getIdTabelaPreco() != null) {
                precoVenda = servicosCategoriasTO.getIdTabelaPreco().getPrecoVenda().toString();
            }
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
            this.listarServicos();
            return "/app/servicos/servicosListar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public void listarServicos() {
        if (idServicoGrupo != null && !idServicoGrupo.isEmpty()) {
            servicosTO = new ServicosTO();
            servicosTO.setIdServicoGrupo(new ServicosGruposTO(new Integer(idServicoGrupo)));
            servicosTOs = servicosNegocio.listar(servicosTO);
        } else {
            servicosTOs = servicosNegocio.listar(new ServicosTO());
        }
    }

    public void listarServicosCategorias() {
        if (idServico != null && !idServico.isEmpty()) {
            servicosTO = servicosNegocio.consultar(new ServicosTO(new Integer(idServico)));
            if (servicosTO.getIdTabelaPreco() != null) {
                precoVenda = servicosTO.getIdTabelaPreco().getPrecoVenda().toString();
            }
        }
    }

    public void listarServicosGrupos() {
        servicosGruposTOs = servicosGruposNegocio.listar(new ServicosGruposTO());
    }

    public String novo() {
        servicosTO = new ServicosTO();
        servicosCategoriasTO = new ServicosCategoriasTO();
        idServico = null;
        idServicoCategoria = null;
        idServicoGrupo = null;
        precoVenda = new String();
        valorACobrar = new String();
        return "/app/servicos/servicosNovo";
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

    public ServicosGruposBO getServicosGruposNegocio() {
        return servicosGruposNegocio;
    }

    public void setServicosGruposNegocio(ServicosGruposBO servicosGruposNegocio) {
        this.servicosGruposNegocio = servicosGruposNegocio;
    }

    public ServicosTO getServicosTO() {
        return servicosTO;
    }

    public void setServicosTO(ServicosTO servicosTO) {
        this.servicosTO = servicosTO;
    }

    public ServicosCategoriasTO getServicosCategoriasTO() {
        return servicosCategoriasTO;
    }

    public void setServicosCategoriasTO(ServicosCategoriasTO servicosCategoriasTO) {
        this.servicosCategoriasTO = servicosCategoriasTO;
    }

    public List<ServicosTO> getServicosTOs() {
        return servicosTOs;
    }

    public void setServicosTOs(List<ServicosTO> servicosTOs) {
        this.servicosTOs = servicosTOs;
    }

    public List<ServicosGruposTO> getServicosGruposTOs() {
        return servicosGruposTOs;
    }

    public void setServicosGruposTOs(List<ServicosGruposTO> servicosGruposTOs) {
        this.servicosGruposTOs = servicosGruposTOs;
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

    public String getIdServicoGrupo() {
        return idServicoGrupo;
    }

    public void setIdServicoGrupo(String idServicoGrupo) {
        this.idServicoGrupo = idServicoGrupo;
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
