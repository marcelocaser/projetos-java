package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Bairros;
import br.com.core.entity.BairrosTO;
import br.com.core.persistence.BairrosPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class BairrosBO implements Bairros {

    @Autowired
    BairrosPO persistencia;

    @Override
    public void alterar(BairrosTO bairrosTO) {
        this.persistencia.alterar(bairrosTO);
    }

    @Override
    public void excluir(BairrosTO bairrosTO) {
        this.persistencia.excluir(bairrosTO);
    }

    @Override
    public void incluir(BairrosTO bairrosTO) {
        this.persistencia.incluir(bairrosTO);
    }

    @Override
    public BairrosTO consultar(BairrosTO bairrosTO) {
        return this.persistencia.consultar(bairrosTO);
    }

    @Override
    public BairrosTO consultaBairrosPeloNome(String nome, String uf) {
        return this.persistencia.consultaBairrosPeloNome(nome, uf);
    }

    @Override
    public List<BairrosTO> listar(BairrosTO bairrosTO) {
        return this.persistencia.listar(bairrosTO);
    }

}
