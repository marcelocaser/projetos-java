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
    UsuariosBO usuariosNegocio;
    @Autowired
    MenusBean menusBean;
    UsuariosTO usuariosTO;
    String senha;
    String email;
    Boolean continuarConectado;

    /* Métodos para tratamento do negócio. */
    public String login() {
        try {
            usuariosTO = new UsuariosTO();
            usuariosTO.setEmail(email);
            usuariosTO.setSenha(CriptografiaUtil.encryptBase64(senha));
            usuariosTO = usuariosNegocio.consultar(usuariosTO);
            if (usuariosTO == null) {
                setMessage("usuariosUsuarioOuSenhaInvalido", EnumTipoMensagem.INFO);
                return URL_INIT;
            }
            setParamSession(EnumSecurity.USUARIO_LOGADO, usuariosTO);
            menusBean.geraMenu();
            return URL_DASHBOARD.concat(REDIRECT);
        } catch (Exception ex) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getContinuarConectado() {
        return continuarConectado;
    }

    public void setContinuarConectado(Boolean continuarConectado) {
        this.continuarConectado = continuarConectado;
    }

}
