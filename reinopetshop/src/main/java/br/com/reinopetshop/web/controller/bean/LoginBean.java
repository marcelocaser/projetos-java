package br.com.reinopetshop.web.controller.bean;

import br.com.core.controller.business.UsuariosBO;
import br.com.core.entity.UsuariosTO;
import br.com.core.enumerator.EnumSecurity;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.util.CriptografiaUtil;
import br.com.reinopetshop.business.controller.ReinoPetController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce_000
 */
@Component
@Scope("session")
public class LoginBean extends ReinoPetController {

    @Autowired
    private UsuariosBO usuariosNegocio;
    private MenusBean menusBean;
    private SistemasConfiguracoesBean sistemasConfiguracoesBean;
    private UsuariosTO usuariosTO;
    private String senha;
    private String emailLogin;
    private Boolean continuarConectado;

    /* Métodos para tratamento do negócio. */
    public String login() {
        try {
            usuariosTO = new UsuariosTO();
            usuariosTO.setEmail(emailLogin);
            usuariosTO.setSenha(CriptografiaUtil.encryptBase64(senha));
            usuariosTO = usuariosNegocio.consultar(usuariosTO);
            if (usuariosTO == null) {
                usuariosTO = new UsuariosTO();
                usuariosTO.setLogin(emailLogin);
                usuariosTO.setSenha(CriptografiaUtil.encryptBase64(senha));
                usuariosTO = usuariosNegocio.consultar(usuariosTO);
                if (usuariosTO == null) {
                    setMessage("usuariosUsuarioOuSenhaInvalido", EnumTipoMensagem.ATENCAO);
                    return URL_INIT;
                }
            }
            setParamSession(EnumSecurity.USUARIO_LOGADO, usuariosTO);
            this.getMenusBean().geraMenu();
            this.getSistemasConfiguracoesBean().executarConfiguracoesDoSistema();
            return URL_DASHBOARD.concat(REDIRECT);
        } catch (Exception ex) {
            try {
                removerAcesso();
            } catch (Exception e) {
                return tratarExcecao(ex);
            }
            return tratarExcecao(ex);
        }
    }

    public String logout() {
        try {
            removerAcesso();
        } catch (Exception ex) {
            setMessage("usuariosErroLogout", EnumTipoMensagem.ERRO);
        }
        return URL_LOGIN.concat(REDIRECT);
    }

    /* Métodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public MenusBean getMenusBean() {
        if (menusBean == null) {
            menusBean = findBean("menusBean");
        }
        return menusBean;
    }

    public void setMenusBean(MenusBean menusBean) {
        this.menusBean = menusBean;
    }

    public SistemasConfiguracoesBean getSistemasConfiguracoesBean() {
        if (sistemasConfiguracoesBean == null) {
            sistemasConfiguracoesBean = findBean("sistemasConfiguracoesBean");
        }
        return sistemasConfiguracoesBean;
    }

    public void setSistemasConfiguracoesBean(SistemasConfiguracoesBean sistemasConfiguracoesBean) {
        this.sistemasConfiguracoesBean = sistemasConfiguracoesBean;
    }

    public UsuariosTO getUsuariosTO() {
        return usuariosTO;
    }

    public void setUsuariosTO(UsuariosTO usuariosTO) {
        this.usuariosTO = usuariosTO;
    }

    public String recuperarSenha() {
        return URL_CADASTRO;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public Boolean getContinuarConectado() {
        return continuarConectado;
    }

    public void setContinuarConectado(Boolean continuarConectado) {
        this.continuarConectado = continuarConectado;
    }

}
