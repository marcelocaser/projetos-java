package br.com.core.persistence.interfaces;

import br.com.core.entity.UsuariosTO;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Usuarios {

    public void alterar(UsuariosTO usuariosTO);

    public void excluir(UsuariosTO usuariosTO);

    public void incluir(UsuariosTO usuariosTO);

    public UsuariosTO consultar(UsuariosTO usuariosTO);

    public List<UsuariosTO> listar(UsuariosTO usuariosTO);

}
