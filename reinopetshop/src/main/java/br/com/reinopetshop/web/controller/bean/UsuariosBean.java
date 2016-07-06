package br.com.reinopetshop.web.controller.bean;

import br.com.core.controller.business.UsuariosBO;
import br.com.core.entity.UsuariosTO;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.persistence.interfaces.Usuarios;
import br.com.reinopetshop.business.controller.ReinoPetController;
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
public class UsuariosBean extends ReinoPetController {

    @Autowired
    UsuariosBO usuariosNegocio;
    private UsuariosTO usuariosTO;
    private List<UsuariosTO> usuariosTOs;
    private String confirmarSenha;
    private Boolean isCPF;
    private Boolean isNovaChave;

    /* Metodos para tratamento do negocio. */
    public String consultar() {
        try {
            this.tipoDeCadastro();
            return "/app/usuarios/usuariosConsultar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String editar() {
        try {
            this.tipoDeCadastro();
            return "/app/usuarios/usuariosEditar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String excluir() {
        try {
            this.usuariosNegocio.excluir(usuariosTO);
            return this.listar();
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String incluir() {
        try {
            if (usuariosTO != null) {
                if (usuariosTO.getId() == null) {
                    this.usuariosNegocio.incluir(usuariosTO);
                    setMessage("usuariosCadastradoComSucesso", EnumTipoMensagem.INFO);
                    return this.listar();
                } else {
                    this.usuariosNegocio.alterar(usuariosTO, isNovaChave);
                    setMessage("usuariosAlteradoComSucesso", EnumTipoMensagem.INFO);
                    return this.consultar();
                }
            }
            return "";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String listar() {
        try {
            usuariosTOs = usuariosNegocio.listarUsuarios();
            return "/app/usuarios/usuariosListar";
        } catch (Exception e) {
            return tratarExcecao(e);
        }
    }

    public String novo() {
        usuariosTO = new UsuariosTO();
        usuariosTO.setTipoCadastro(Usuarios.PESSOA_FISICA);
        isCPF = Boolean.TRUE;
        isNovaChave = Boolean.FALSE;
        return "/app/usuarios/usuariosNovo";
    }

    public void tipoDeCadastro() {
        isCPF = Boolean.TRUE;
        if (usuariosTO != null && usuariosTO.getTipoCadastro().equals(Usuarios.PESSOA_JURIDICA)) {
            isCPF = Boolean.FALSE;
        }
    }

    /* Metodos para tratamento de eventos e de tela em geral. Evite mudar. */
    public UsuariosTO getUsuariosTO() {
        return usuariosTO;
    }

    public void setUsuariosTO(UsuariosTO usuariosTO) {
        this.usuariosTO = usuariosTO;
    }

    public List<UsuariosTO> getUsuariosTOs() {
        return usuariosTOs;
    }

    public void setUsuariosTOs(List<UsuariosTO> usuariosTOs) {
        this.usuariosTOs = usuariosTOs;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public Boolean getIsCPF() {
        return isCPF;
    }

    public void setIsCPF(Boolean isCPF) {
        this.isCPF = isCPF;
    }

    public Boolean getIsNovaChave() {
        return isNovaChave;
    }

    public void setIsNovaChave(Boolean isNovaChave) {
        this.isNovaChave = isNovaChave;
    }

}
