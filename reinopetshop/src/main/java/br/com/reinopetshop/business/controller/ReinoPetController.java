package br.com.reinopetshop.business.controller;

import br.com.core.controller.Controller;
import br.com.core.entity.SistemasConfiguracoesTO;
import br.com.core.entity.UsuariosTO;
import br.com.core.enumerator.EnumSecurity;
import br.com.core.enumerator.EnumTipoMensagem;
import br.com.core.exception.AcessoException;
import br.com.core.exception.NegocioException;
import br.com.core.message.Mensagem;
import br.com.core.message.MensagemLista;
import br.com.core.resource.ResourceServiceUtil;
import br.com.core.util.ExceptionUtil;
import br.com.core.util.FacesUtil;
import br.com.reinopetshop.business.constants.ReinoPetConstantes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.persistence.PersistenceException;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author marcelocaser
 */
public class ReinoPetController extends Controller implements ReinoPetConstantes {

    private static final long serialVersionUID = 1L;

    /**
     * Retorna o usuário logado.
     *
     * @return UsuarioTO
     */
    public UsuariosTO getUsuarioLogado() {
        return (UsuariosTO) getParamSession(EnumSecurity.USUARIO_LOGADO);
    }

    /**
     * Retorna as configuracoes do sistema.
     *
     * @return SistemasConfiguracoesTO
     */
    public SistemasConfiguracoesTO getConfiguracaoDoSistema() {
        return (SistemasConfiguracoesTO) getParamSession(EnumSecurity.SISTEMA_CONFIGURACAO);
    }

    /**
     * Insere um parâmetro no request.
     *
     * @param alias - AdmNetEnumSecurity
     * @param parameter - Object
     */
    protected void setParamRequest(EnumSecurity alias, Object parameter) {
        FacesUtil.setParamRequest(this.getContext(), alias.name(), parameter);
    }

    /**
     * Dispara mensagem via modal panel no request.
     *
     * @param key - {@link String} - Chave de texto existente no arquivo de
     * internacionalização.
     * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem -
     * [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
     */
    protected void setMessage(String key, EnumTipoMensagem tipo) {
        setParamRequest(EnumSecurity.MODAL_MENSAGEM, Boolean.TRUE);
        setParamRequest(EnumSecurity.TIPO_MENSAGEM, tipo);
        if (tipo.equals(EnumTipoMensagem.INFO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", this.getMessage(key)));
        }
        if (tipo.equals(EnumTipoMensagem.ERRO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops...", this.getMessage(key)));
        }
        if (tipo.equals(EnumTipoMensagem.ATENCAO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", this.getMessage(key)));
        }
        if (tipo.equals(EnumTipoMensagem.FATAL)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_FATAL, "Que feio servidor...", this.getMessage(key)));
        }
    }

    /**
     * Dispara mensagem via modal panel no request.
     *
     * @param key - {@link String} - Chave de texto existente no arquivo de
     * internacionalização.
     * @param parametros - {@link String[]} - Lista de parametros que serão
     * inseridos na mensagem.
     * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem -
     * [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
     */
    protected void setMessage(String key, String[] parametros, EnumTipoMensagem tipo) {
        setParamRequest(EnumSecurity.MODAL_MENSAGEM, Boolean.TRUE);
        setParamRequest(EnumSecurity.TIPO_MENSAGEM, tipo);
        if (tipo.equals(EnumTipoMensagem.INFO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", this.getMessage(key, parametros)));
        }
        if (tipo.equals(EnumTipoMensagem.ERRO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops...", this.getMessage(key, parametros)));
        }
        if (tipo.equals(EnumTipoMensagem.ATENCAO)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", this.getMessage(key, parametros)));
        }
        if (tipo.equals(EnumTipoMensagem.FATAL)) {
            FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_FATAL, "Que feio servidor...", this.getMessage(key, parametros)));
        }
    }

    /**
     * Adiciona uma lista de mensagens no request
     *
     * @param listaMensagem
     * @param tipo - int
     */
    protected void setMessage(List<Mensagem> listaMensagem, EnumTipoMensagem tipo) {
        setParamRequest(EnumSecurity.MODAL_MENSAGEM, Boolean.TRUE);
        setParamRequest(EnumSecurity.TIPO_MENSAGEM, tipo);
        for (Mensagem msg : listaMensagem) {
            if (tipo.equals(EnumTipoMensagem.INFO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
            }
            if (tipo.equals(EnumTipoMensagem.ERRO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops...", ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
            }
            if (tipo.equals(EnumTipoMensagem.ATENCAO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
            }
            if (tipo.equals(EnumTipoMensagem.FATAL)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_FATAL, "Que feio servidor...", ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
            }
        }
    }

    /**
     * Adiciona uma lista de mensagens no request
     *
     * @param listaMensagem
     * @param tipo - int
     */
    protected void setMessage(MensagemLista listaMensagem, EnumTipoMensagem tipo) {
        setParamRequest(EnumSecurity.MODAL_MENSAGEM, Boolean.TRUE);
        setParamRequest(EnumSecurity.TIPO_MENSAGEM, tipo);
        for (Mensagem msg : listaMensagem.getMensagens()) {
            if (tipo.equals(EnumTipoMensagem.INFO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_INFO, ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null), null));
            }
            if (tipo.equals(EnumTipoMensagem.ERRO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null), null));
            }
            if (tipo.equals(EnumTipoMensagem.ATENCAO)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_WARN, ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null), null));
            }
            if (tipo.equals(EnumTipoMensagem.FATAL)) {
                FacesUtil.addMessageCtx(this.getContext(), new FacesMessage(FacesMessage.SEVERITY_FATAL, ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null), null));
            }
        }
    }

    /**
     * Retorna o contexto do sistema
     *
     * @return String
     */
    protected String getContextPath() {
        return FacesUtil.getRequest(getContext()).getContextPath();
    }

    /**
     * Insere parametros do controle de acesso na sessão
     *
     * @param key - AdmNetEnumSecurity
     * @param parameter - Object
     */
    protected void setParamSession(EnumSecurity key, Object parameter) {
        super.setParamSession(key.name(), parameter);
    }

    /**
     * Remove parametros do controle de acesso na sessão
     *
     * @param key - AdmNetEnumSecurity
     */
    protected void removeParamSession(EnumSecurity key) {
        super.removeParamSession(key.name());
    }

    /**
     * Retorna o caminho real de um arquivo
     *
     * @param file
     * @return - O caminho real do arquivo
     */
    protected String getRealPathFile(String file) {
        return FacesUtil.getRealPathFile(this.getContext(), file);
    }

    /**
     * Remove a autenticação do usuáio no controle de acesso.
     *
     * @throws java.lang.Exception
     */
    protected void removerAcesso() throws Exception {
        //Destroi a sessão do Usuario
        sessionDestroy();
        //Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
        //SistemaTO sistema = negocioSistema.consultarSistemaContexto(getRequest().getContextPath());
        //Redireciona para a página inicial do sistema.
    }

    /**
     * Retorna parametros do controle de acesso armazenados na sessão
     *
     * @param key - AdmNetEnumSecurity
     * @return Object
     */
    protected Object getParamSession(EnumSecurity key) {
        return super.getParamSession(key.name());
    }

    /**
     * Execute a javascript after current ajax request is completed.
     *
     * @param javaScript - Javascript statement to execute
     */
    protected void primefacesExecute(String javaScript) {
        if (javaScript != null && !javaScript.isEmpty()) {
            RequestContext.getCurrentInstance().execute(javaScript);
        }
    }

    /**
     * Update a component with ajax.
     *
     * @param idComponente - Client side identifiers of the components
     */
    protected void primefacesUpdate(String idComponente) {
        if (idComponente != null && !idComponente.isEmpty()) {
            RequestContext.getCurrentInstance().update(idComponente);
        }
    }

    /**
     * Add a parameter for ajax oncomplete client side callbacks. Value would be
     * serialized to json. Currently supported values are plain objects,
     * primitives, JSONObject and JSONArray.
     *
     * @param name - name of the parameter
     * @param value - value of the parameter
     */
    protected void primefacesAddParamCallback(String name, Boolean value) {
        if (name != null && !name.isEmpty() && value != null) {
            RequestContext.getCurrentInstance().addCallbackParam(name, value);
        }
    }

    /**
     * Tratamento de exceções para filtrar as mensagens emitidas ao usuário
     *
     * @param e - {@link Exception}
     * @return
     */
    protected String tratarExcecao(Exception e) {
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        //Busca a origem de exceções não declaradas
        if (e instanceof UndeclaredThrowableException) {
            e = ExceptionUtil.getException(e);
        } else if (e instanceof InvocationTargetException) {
            e = ExceptionUtil.getException(e);
        }

        //exceções customizadas
        if (e instanceof NegocioException) {
            mensagens = ((NegocioException) e).getMensagens();
        } else if (e instanceof PersistenceException) {
            mensagens.add(new Mensagem(e.getMessage()));
        } else if (e instanceof AcessoException) {
            mensagens = ((AcessoException) e).getMensagens();
        } else if (e instanceof DataIntegrityViolationException) {
            mensagens.add(new Mensagem(getMessage("admDataIntegrityViolationException")));
        } else if (e instanceof NullPointerException) {
            mensagens.add(new Mensagem(getMessage("admNullPointerException")));
        } else if (e instanceof ViewExpiredException) {
            mensagens.add(new Mensagem("sessaoExpirada"));
        } else {
            mensagens.add(new Mensagem(e.getMessage()));
        }
        e.printStackTrace();
        this.setMessage(mensagens, EnumTipoMensagem.ERRO);
        return null;
    }
}
