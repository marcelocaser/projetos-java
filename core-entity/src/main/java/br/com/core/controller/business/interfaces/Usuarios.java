package br.com.core.controller.business.interfaces;

import br.com.core.entity.UsuariosTO;
import br.com.core.exception.NegocioException;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Usuarios {

    public static final Character ATIVO = 'A';
    public static final Character INATIVO = 'I';
    public static final Character EXCLUIDO = 'E';

    public static final Character PESSOA_FISICA = 'F';
    public static final Character PESSOA_JURIDICA = 'J';

    public void alterar(UsuariosTO usuariosTO);

    public void alterar(UsuariosTO usuariosTO, Boolean isNovaChave) throws NegocioException;

    public void excluir(UsuariosTO usuariosTO);

    public UsuariosTO gerarChaveAcesso(UsuariosTO usuariosTO);

    public UsuariosTO gerarSenhaBase64(UsuariosTO usuariosTO) throws NegocioException;

    public void incluir(UsuariosTO usuariosTO) throws NegocioException;

    public UsuariosTO consultar(UsuariosTO usuariosTO);

    public boolean isChaveDeAcessoValida(String chave);

    public List<UsuariosTO> listar(UsuariosTO usuariosTO);

    public List<UsuariosTO> listarUsuarios();

}
