package br.com.core.controller.business;

import br.com.core.entity.ConsultasTO;
import br.com.core.entity.UsuariosTO;
import br.com.core.persistence.interfaces.Consultas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class ConsultasBO {

    @Autowired
    Consultas persistencia;

    public void alterar(ConsultasTO consultasTO) {
        this.persistencia.alterar(consultasTO);
    }

    public List<ConsultasTO> consultarEstatisticaCEPConsultados(UsuariosTO usuariosTO, Integer[] range) {
        return this.persistencia.consultarEstatisticaCEPConsultados(usuariosTO, range);
    }

    public Integer estatisticaTotalDeCEPDesdeCadastro(UsuariosTO usuariosTO) {
        return this.persistencia.estatisticaTotalDeCEPDesdeCadastro(usuariosTO);
    }

    public void excluir(ConsultasTO consultasTO) {
        this.persistencia.excluir(consultasTO);
    }

    public void incluir(ConsultasTO consultasTO) {
        this.persistencia.incluir(consultasTO);
    }

    public ConsultasTO consultar(ConsultasTO consultasTO) {
        return this.persistencia.consultar(consultasTO);
    }

    public List<ConsultasTO> listar(ConsultasTO consultasTO) {
        return this.persistencia.listar(consultasTO);
    }

}
