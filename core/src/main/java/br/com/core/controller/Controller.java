package br.com.core.controller;

import br.com.core.resource.ResourceServiceUtil;
import br.com.core.util.FacesUtil;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcelocaser
 */
public class Controller implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String REDIRECT = "?faces-redirect=true";

    private Application application;

    /**
     * Retorna o valor armazenado na variável ManagedBean.java.
     *
     * @return FacesContext
     */
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
    /**
     * Retorna o contexto externo atual.
     * 
     * @return Contexto externo.
     */
    protected ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    /**
     * Retorna o valor armazenado na variável Controller.java.
     *
     * @return Application
     */
    protected Application getApplication() {
        if (application == null) {
            application = getContext().getApplication();
        }
        return application;
    }

    /**
     * Insere um valor ao atributo Controller.java
     *
     * @param application - Application
     *
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Adiciona um array de bytes no response.
     *
     * @param bytes - byte[]
     * @throws IOException
     */
    protected void setResponseWriter(byte[] bytes) throws IOException {
        FacesUtil.setResponseWriter(this.getContext(), bytes);
    }

    /**
     * Adiciona um array de bytes no response.
     *
     * @param bytes - byte[]
     * @param contentType - String
     *
     * @throws IOException
     */
    protected void setResponseWriter(byte[] bytes, String contentType) throws IOException {
        FacesUtil.setResponseWriter(this.getContext(), bytes, contentType);
    }

    /**
     * Adiciona um array de bytes no response.
     *
     * @param bytes - byte[]
     * @param contentType - String
     * @param nome
     *
     * @throws IOException
     */
    protected void setResponseWriter(byte[] bytes, String contentType, String nome) throws IOException {
        FacesUtil.setResponseWriter(this.getContext(), bytes, contentType, nome);
    }

    /**
     * Adiciona parametros na sessão do usuário.
     *
     * @param alias - String
     * @param parameter - Object
     */
    protected void setParamSession(String alias, Object parameter) {
        FacesUtil.setParamSession(this.getContext(), alias, parameter);
    }

    /**
     * Cria uma expressão para de retorno para um objeto no JAVA.
     *
     * @param expressao - {@link String}
     * @param tipoRetorno - {@link Class}
     * @param argumentos - {@link Class}
     * @return {@link MethodExpression}
     *
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException -
     */
    @SuppressWarnings("unchecked")
    public MethodExpression createMethodExpression(String expressao, Class tipoRetorno, Class[] argumentos) throws Exception {
        return getContext().getApplication().getExpressionFactory().createMethodExpression(getContext().getELContext(), expressao, tipoRetorno, argumentos);
    }

    /**
     * Cria uma expressão para de retorno para um objeto no JAVA.
     *
     * @param expressao - {@link String}
     * @param tipoRetorno - {@link Class}
     * @return {@link ValueExpression}
     *
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public ValueExpression getValueExpression(String expressao, Class tipoRetorno) throws Exception {
        return FacesUtil.getValueExpression(getContext(), expressao, tipoRetorno);
    }

    /**
     * Destroi a sessão corrente do usuário, se este método for utilizado, o
     * usuário atual e desligado da aplicação.
     */
    protected void sessionDestroy() {
        FacesUtil.sessionDestroy(this.getContext());
    }

    /**
     * Retorna um parâmetro armazenado na Sessão
     *
     * @param alias - String
     * @return Object
     */
    protected Object getParamSession(String alias) {
        return FacesUtil.getParamSession(this.getContext(), alias);
    }

    /**
     * Retorna um parâmetro armazenado no Request
     *
     * @param alias - String
     * @return Object
     */
    protected Object getParamRequest(String alias) {
        return FacesUtil.getParamRequest(this.getContext(), alias);
    }

    /**
     * Retorna o parametro passado pela URL
     *
     * @param alias - String
     * @return String
     */
    protected String getParamUrl(String alias) {
        return FacesUtil.getParamUrl(this.getContext(), alias);
    }

    /**
     * Remove um atributo da Sessão.
     *
     * @param alias - String
     */
    protected void removeParamSession(String alias) {
        FacesUtil.removeParamSession(this.getContext(), alias);
    }

    /**
     * Insere um parâmetro no request.
     *
     * @param alias - String
     * @param parameter - Object
     */
    protected void setParamRequest(String alias, Object parameter) {
        FacesUtil.setParamRequest(this.getContext(), alias, parameter);
    }

    /**
     * Retorna o texto do arquivo de internacionalização a partir da chave.
     *
     * @param key - String
     * @return String
     */
    protected String getMessage(String key) {
        return ResourceServiceUtil.getMessageResourceString(key, null);
    }

    /**
     * Retorna o texto do arquivo de internacionalização a partir da chave.
     *
     * @param key - String
     * @param param
     *
     * @return String
     */
    protected String getMessage(String key, Object[] param) {
        return ResourceServiceUtil.getMessageResourceString(key, param);
    }

    /**
     * Retorna o objeto Request atual
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesUtil.getRequest(this.getContext());
    }

    /**
     * Retorna o objeto Response atual
     *
     * @return HttpServletResponse
     */
    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesUtil.getResponse(this.getContext());
    }

    /**
     * Retorna o objeto Session atual
     *
     * @return HttpSession
     */
    protected HttpSession getSession() {
        return (HttpSession) FacesUtil.getSession(this.getContext());
    }

    /**
     * Retorna o objeto Session atual
     *
     * @param atributo
     *
     * @return HttpSession
     */
    protected HttpSession getSession(boolean atributo) {
        return (HttpSession) FacesUtil.getSession(this.getContext(), atributo);
    }

    /**
     * Dispara um foward no FacesContext
     *
     * @param viewId - String
     */
    protected void forward(String viewId) {
        FacesUtil.foward(getContext(), getApplication().getViewHandler(), viewId);
    }

    /**
     * Realiza uma navegacao JSF - Forward
     *
     * @param target - String de Navegacao definida no arquivo
     * navigation-rules.xml
     */
    protected void handleNavigation(String target) {
        FacesUtil.handleNavigation(getContext(), target);
    }

    /**
     * Redireciona para a URL especificada
     *
     * @param url - String
     *
     * @throws java.io.IOException
     */
    protected void redirect(String url) throws IOException {
        FacesUtil.redirect(getContext(), url);
    }

    /**
     * Retorna o valor de um determinado Key do arquivo de internacionalização
     *
     * @param key - String
     * @return String
     */
    protected String getTextMessageRessource(String key) {
        return this.getTextMessageRessource(key, null);
    }

    /**
     * Retorna o valor de um determinado Key do arquivo de internacionalização
     *
     * @param key - String
     * @param param - Object[]
     * @return String
     */
    protected String getTextMessageRessource(String key, Object[] param) {
        return ResourceServiceUtil.getMessageResourceString(key, param);
    }

    /**
     * Retorna a lista de conversores controlada pela App
     *
     * @return Iterator
     */
    protected Iterator<String> getConverterIds() {
        return FacesUtil.getConverterIds(this.getContext());
    }

    /**
     * Retorna a lista de validadores controlada pela App
     *
     * @return Iterator
     */
    protected Iterator<String> getValidatorIds() {
        return FacesUtil.getValidatorIds(this.getContext());
    }

    /**
     * Retorna um parâmetro passado pela View.
     *
     * @param event - ActinEvent
     * @param key - String
     * @return ManagedBean
     */
    protected Object getParamView(ActionEvent event, String key) {
        return FacesUtil.getParamView(event, key);
    }

    /**
     * Retorna a instância de um ManagedBean gerênciado pelo JSF
     *
     * @param alias - String
     * @return ManagedBean
     */
    protected Controller getManagedBean(String alias) {
        return (Controller) FacesUtil.getManagedBean(this.getContext(), alias);
    }
    
    /**
     * Retorna informações sobre o navegador.
     * 
     * @return Valor da propriedade informada.
     */
    protected OperatingSystem getOperatingSystem() {
        UserAgent userAgent = UserAgent.parseUserAgentString(getExternalContext().getRequestHeaderMap().get("User-Agent"));
        return userAgent.getOperatingSystem();
    }

    /**
     * Retorna a instância de um Bean gerênciado pelo Spring.
     *
     * @param <T> Instância de num bean.
     * @param beanName Nome do bean do qual irá buscar uma instância.
     * @return Retorna a instância de um bean. Caso não exista retorna null.
     */
    protected <T> T findBean(String beanName) {
        return (T) getContext().getApplication().evaluateExpressionGet(getContext(), "#{" + beanName + "}", Object.class);
    }

}
