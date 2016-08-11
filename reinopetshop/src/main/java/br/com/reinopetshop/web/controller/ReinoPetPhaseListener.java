package br.com.reinopetshop.web.controller;

import br.com.core.controller.Controller;
import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.enumerator.EnumSecurity;
import br.com.core.util.FacesUtil;
import br.com.reinopetshop.business.constants.ReinoPetConstantes;
import br.com.reinopetshop.web.controller.bean.SistemasConfiguracoesBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marce
 */
public class ReinoPetPhaseListener extends Controller implements PhaseListener, ReinoPetConstantes {

    private SistemasConfiguracoesBean sistemasConfiguracoesBean;

    @Override
    public void afterPhase(PhaseEvent pe) {
        if (pe.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            FacesContext facesContext = pe.getFacesContext();
            this.buscaConfiguracaoDoSistema(facesContext);
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (pe.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    protected void buscaConfiguracaoDoSistema(FacesContext facesContext) {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        SistemasConfiguracoesTO sistemasConfiguracoesTO = (SistemasConfiguracoesTO) session.getAttribute(EnumSecurity.SISTEMA_CONFIGURACAO.name());
        if (sistemasConfiguracoesTO == null) {
            sistemasConfiguracoesBean = findBean("sistemasConfiguracoesBean");
            sistemasConfiguracoesBean.buscarConfiguracoesDoSistema();
            sistemasConfiguracoesTO = sistemasConfiguracoesBean.getSistemasConfiguracoesTO();
            if (sistemasConfiguracoesTO != null) {
                session.setAttribute(EnumSecurity.SISTEMA_CONFIGURACAO.name(), sistemasConfiguracoesTO);
            } else {
                FacesUtil.addMessageCtx(facesContext, new FacesMessage("erroAoBuscarConfiguracoes"));
            }
        }
    }

    protected boolean isRequisicaoValida(FacesContext facesContext) {
        //TODO Nao implementado!!!! Nao utilizado ate o momento!! Implementacao futura!!!
        if (facesContext == null) {
            return false;
        }
        return true;
    }

}
