package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Usuarios;
import br.com.core.entity.UsuariosTO;
import br.com.core.exception.NegocioException;
import br.com.core.persistence.UsuariosPO;
import br.com.core.util.CriptografiaUtil;
import br.com.core.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class UsuariosBO implements Usuarios {

    @Autowired
    UsuariosPO persistencia;

    @Override
    public void alterar(UsuariosTO usuariosTO) {
         this.persistencia.alterar(usuariosTO);
    }

    @Override
    public void alterar(UsuariosTO usuariosTO, Boolean isNovaChave) throws NegocioException {
        antesDeAlterar(usuariosTO);
        if (isNovaChave) {
            usuariosTO = gerarChaveAcesso(usuariosTO);
        }
        usuariosTO = gerarSenhaBase64(usuariosTO);
        this.persistencia.alterar(usuariosTO);
    }

    private void antesDeAlterar(UsuariosTO usuariosTO) {
        usuariosTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(UsuariosTO usuariosTO) {
        usuariosTO.setExclusao(new Date());
        usuariosTO.setStatus(EXCLUIDO);
    }

    @Override
    public UsuariosTO consultar(UsuariosTO usuariosTO) {
        return this.persistencia.consultar(usuariosTO);
    }

    @Override
    public void excluir(UsuariosTO usuariosTO) {
        antesDeExcluir(usuariosTO);
        this.persistencia.alterar(usuariosTO);
    }

    @Override
    public UsuariosTO gerarChaveAcesso(UsuariosTO usuariosTO) {
        if (usuariosTO != null && usuariosTO.getEmail() != null
                && (usuariosTO.getCnpj() != null || usuariosTO.getCpf() != null)) {
            usuariosTO.setChave(CriptografiaUtil.gerarMD5((usuariosTO.getTipoCadastro().equals('F') ? usuariosTO.getCpf() : usuariosTO.getCnpj()).concat(usuariosTO.getEmail().concat("CEPGo").concat(DateUtil.formataData(new Date(), "ddMMYYYY")))));
        } else {
            //Tratar erro;
        }
        return usuariosTO;
    }

    @Override
    public UsuariosTO gerarSenhaBase64(UsuariosTO usuariosTO) throws NegocioException {
        if (usuariosTO != null && usuariosTO.getSenha() != null) {
            usuariosTO.setSenha(CriptografiaUtil.encryptBase64(usuariosTO.getSenha()));
        } else {
            //tratar erro;
        }
        return usuariosTO;
    }

    @Override
    public void incluir(UsuariosTO usuariosTO) throws NegocioException {
        usuariosTO.setStatus(ATIVO);
        usuariosTO = gerarChaveAcesso(usuariosTO);
        usuariosTO = gerarSenhaBase64(usuariosTO);
        UsuariosTO usuario = new UsuariosTO();
        usuario.setCpf(usuariosTO.getCpf());
        usuario.setCnpj(usuariosTO.getCnpj());
        usuario = this.consultar(usuario);
        if (usuario != null && usuario.getId() != null) {
            throw new NegocioException("usuariosJaCadastrado");
        }
        this.persistencia.incluir(usuariosTO);
    }

    @Override
    public boolean isChaveDeAcessoValida(String chave) {
        if (chave != null && !chave.isEmpty()) {
            UsuariosTO usuariosTO = new UsuariosTO();
            usuariosTO.setChave(chave);
            usuariosTO.setStatus(ATIVO);
            if (this.persistencia.listar(usuariosTO).size() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UsuariosTO> listar(UsuariosTO usuariosTO) {
        return this.persistencia.listar(usuariosTO);
    }

    @Override
    public List<UsuariosTO> listarUsuarios() {
        return this.persistencia.listarUsuarios();
    }

}
