package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Cidades;
import br.com.core.entity.CidadesTO;
import br.com.core.persistence.CidadesPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class CidadesBO implements Cidades {

    @Autowired
    CidadesPO persistencia;

    @Override
    public void alterar(CidadesTO cidadesTO) {
        this.persistencia.alterar(cidadesTO);
    }

    @Override
    public void excluir(CidadesTO cidadesTO) {
        this.persistencia.excluir(cidadesTO);

    }

    @Override
    public void incluir(CidadesTO cidadesTO) {
        this.persistencia.incluir(cidadesTO);
    }

    @Override
    public CidadesTO consultar(CidadesTO cidadesTO) {
        return this.persistencia.consultar(cidadesTO);
    }

    @Override
    public List<CidadesTO> listar(CidadesTO cidadesTO) {
        return this.persistencia.listar(cidadesTO);
    }

}
