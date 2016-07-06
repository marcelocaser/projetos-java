package br.com.portalweb.business.controller;

import br.com.core.entity.WebUsuariosTO;
import br.com.core.enumerator.EnumSecurity;
import br.com.portalweb.business.constants.PortalWebConstantes;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcelocaser
 */
//@WebFilter(filterName = "portalweb", urlPatterns = "/*")
public class PortalWebFilter implements Filter, PortalWebConstantes {

    private HttpServletRequest hRequest;
    private HttpSession hSession;
    private String path;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        this.hRequest = (HttpServletRequest) request;
        this.hSession = ((HttpServletRequest) request).getSession();
        this.path = this.hRequest.getServletPath();

        WebUsuariosTO usuarioLogado = null;

        try {
            // Caso exista sessão e sessionId associado ao request seja válido,
            // recupera o atributo de sessão USUARIO_LOGADO.
            if (this.hSession != null
                    && this.hRequest.isRequestedSessionIdValid()) {
                usuarioLogado = (WebUsuariosTO) this.hSession.getAttribute(EnumSecurity.USUARIO_LOGADO.toString());
            }

            // Se usuário logado, aplica o controle de acesso URL. Se não,
            // direciona para a página de LOGIN.
            if (usuarioLogado != null) {
                redirecionarAcessoUsuarioLogado(chain, request, response);
            } else {
                if (!this.path.equals(URL_CADASTRO)) {
                    request.getRequestDispatcher(URL_LOGIN).forward(request, response);
                } else {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }
            }
        } catch (Throwable e) {
            //BaseException ex = Util.getBaseException(e);
            //ex.printStackTrace();
            request.setAttribute(MSG_ERRO_GERAL, Boolean.TRUE);
            request.setAttribute(MSG_ERRO_GERAL_TXT, e.getMessage());
            if (usuarioLogado != null) {
                request.getRequestDispatcher(URL_DASHBOARD).forward(request, response);
            } else {
                request.getRequestDispatcher(URL_LOGIN).forward(request, response);
            }
        }
    }

    private void redirecionarAcessoUsuarioLogado(FilterChain chain,
            ServletRequest request, ServletResponse response) throws Throwable {
        // rotinas de direcionamento para usuário logado
        if (this.path.equals(URL_LOGIN) || this.path.equals(URL_INIT)) {
            request.getRequestDispatcher(URL_DASHBOARD).forward(request, response);
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
