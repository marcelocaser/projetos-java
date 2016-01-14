package br.com.reinopetshop.business.constants;

/**
 *
 * @author marcelocaser
 */
public interface ReinoPetConstantes {

    /**
     * Nome padrão do sistema
     */
    public static final String REINOPET = "REINOPET";

    /**
     * Nome padrão do servidor/dominio
     */
    public static final String ENDERECO = "";
    public static final String DOMINIO = "";

    /**
     * Variáveis para controle de acesso
     */
    public static final String MSG_ERRO_GERAL_TXT = "txtMsgErroGlobal";
    public static final String MSG_ERRO_GERAL = "msgErroFilter";
    public static final String USUARIO_LOGADO = "usuarioLogado";

    /**
     * Chave das mensagens
     */
    public static final String KEY_MSG_SUCESSO_INCLUIR = "msgSucessoIncluir";
    public static final String KEY_MSG_SUCESSO_ALTERAR = "msgSucessoAlterar";
    public static final String KEY_MSG_SUCESSO_EXCLUIR = "msgSucessoExcluir";

    /**
     * URL'S
     */
    public static final String URL_EXT = ".reinopet";
    public static final String URL_INIT = "/reinopetshop";
    public static final String URL_LOGIN = "/app/login".concat(URL_EXT);
    public static final String URL_DASHBOARD = "/app/dashboard".concat(URL_EXT);
    public static final String URL_CADASTRO = "/app/cadastro".concat(URL_EXT);

}
