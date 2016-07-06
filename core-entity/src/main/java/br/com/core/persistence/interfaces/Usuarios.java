package br.com.core.persistence.interfaces;

import br.com.core.entity.UsuariosTO;
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

    public void excluir(UsuariosTO usuariosTO);

    public void incluir(UsuariosTO usuariosTO);

    public UsuariosTO consultar(UsuariosTO usuariosTO);

    public List<UsuariosTO> listar(UsuariosTO usuariosTO);
    
    public List<UsuariosTO> listarUsuarios();

}
