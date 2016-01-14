package br.com.core.controller.business;

import br.com.core.entity.UsuariosTO;
import br.com.core.exception.NegocioException;
import br.com.core.persistence.interfaces.Usuarios;
import br.com.core.util.CriptografiaUtil;
import br.com.core.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marcelocaser
 */
@Component
public class UsuariosBO {

    @Autowired
    Usuarios persistencia;
    
    public void alterar(UsuariosTO usuariosTO) {
        this.persistencia.alterar(usuariosTO);
    }

    public void excluir(UsuariosTO usuariosTO) {
        this.persistencia.excluir(usuariosTO);
    }

    public UsuariosTO gerarChaveAcesso(UsuariosTO usuariosTO) {
        if (usuariosTO != null && usuariosTO.getEmail() != null
                && (usuariosTO.getCnpj() != null || usuariosTO.getCpf() != null)) {
            usuariosTO.setChave(CriptografiaUtil.gerarMD5((usuariosTO.getTipoCadastro().equals('F') ? usuariosTO.getCpf() : usuariosTO.getCnpj()).concat(usuariosTO.getEmail().concat("CEPGo").concat(DateUtil.formataData(new Date(), "ddMMYYYY")))));
        } else {
            //Tratar erro;
        }
        return usuariosTO;
    }

    public UsuariosTO gerarSenhaBase64(UsuariosTO usuariosTO) throws NegocioException {
        if (usuariosTO != null && usuariosTO.getSenha() != null) {
            usuariosTO.setSenha(CriptografiaUtil.encryptBase64(usuariosTO.getSenha()));
        } else {
            //tratar erro;
        }
        return usuariosTO;
    }

    public void incluir(UsuariosTO usuariosTO) throws NegocioException {
        usuariosTO.setCpf(usuariosTO.getCpf() != null ? usuariosTO.getCpf().replaceAll("[.-]", "") : null);
        usuariosTO.setCnpj(usuariosTO.getCnpj() != null ? usuariosTO.getCnpj().replaceAll("./-", "") : null);
        usuariosTO.setTelefone(usuariosTO.getTelefone().replaceAll("[()-]", ""));
        usuariosTO = gerarChaveAcesso(usuariosTO);
        usuariosTO = gerarSenhaBase64(usuariosTO);
        this.persistencia.incluir(usuariosTO);
    }

    public UsuariosTO consultar(UsuariosTO usuariosTO) {
        return this.persistencia.consultar(usuariosTO);
    }

    public boolean isChaveDeAcessoValida(String chave) {
        if (chave != null && !chave.isEmpty()) {
            UsuariosTO usuariosTO = new UsuariosTO();
            usuariosTO.setChave(chave);
            usuariosTO.setStatus('A');
            if (this.persistencia.listar(usuariosTO).size() > 0) {
                return true;
            }
        }
        return false;
    }

    public List<UsuariosTO> listar(UsuariosTO usuariosTO) {
        return this.persistencia.listar(usuariosTO);
    }

}
