package br.com.core.persistence;

import br.com.core.entity.UsuariosTO;
import br.com.core.persistence.interfaces.Usuarios;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelocaser
 */
@Component
public class UsuariosPO extends Persistence<UsuariosTO> implements Usuarios {

    public UsuariosPO() {
        setClazz(UsuariosTO.class);
    }

    @Override
    @Transactional
    public void alterar(UsuariosTO usuariosTO) {
        update(usuariosTO);
    }

    @Override
    @Transactional
    public void excluir(UsuariosTO usuariosTO) {
        delete(usuariosTO);
    }

    @Override
    @Transactional
    public void incluir(UsuariosTO usuariosTO) {
        create(usuariosTO);
    }

    @Override
    public UsuariosTO consultar(UsuariosTO usuariosTO) {
        return find(usuariosTO);
    }

    @Override
    public List<UsuariosTO> listar(UsuariosTO usuariosTO) {
        return list(usuariosTO);
    }

}
