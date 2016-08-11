package br.com.core.controller.business;

import br.com.core.entity.CidadesTO;
import br.com.core.persistence.interfaces.Cidades;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class CidadesBO {

    @Autowired
    Cidades persistencia;

    public void alterar(CidadesTO cidadesTO) {
        this.persistencia.alterar(cidadesTO);
    }

    public void excluir(CidadesTO cidadesTO) {
        this.persistencia.excluir(cidadesTO);

    }

    public void incluir(CidadesTO cidadesTO) {
        this.persistencia.incluir(cidadesTO);
    }

    public CidadesTO consultar(CidadesTO cidadesTO) {
        return this.persistencia.consultar(cidadesTO);
    }

    public List<CidadesTO> listar(CidadesTO cidadesTO) {
        return this.persistencia.listar(cidadesTO);
    }

}
