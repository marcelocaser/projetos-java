package br.com.core.controller.business;

import br.com.core.controller.business.interfaces.Consultas;
import br.com.core.entity.ConsultasTO;
import br.com.core.entity.UsuariosTO;
import br.com.core.persistence.ConsultasPO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class ConsultasBO implements Consultas {

    @Autowired
    ConsultasPO persistencia;

    @Override
    public void alterar(ConsultasTO consultasTO) {
        this.persistencia.alterar(consultasTO);
    }

    @Override
    public List<ConsultasTO> consultarEstatisticaCEPConsultados(UsuariosTO usuariosTO, Integer[] range) {
        return this.persistencia.consultarEstatisticaCEPConsultados(usuariosTO, range);
    }

    @Override
    public Integer estatisticaTotalDeCEPDesdeCadastro(UsuariosTO usuariosTO) {
        return this.persistencia.estatisticaTotalDeCEPDesdeCadastro(usuariosTO);
    }

    @Override
    public void excluir(ConsultasTO consultasTO) {
        this.persistencia.excluir(consultasTO);
    }

    @Override
    public void incluir(ConsultasTO consultasTO) {
        this.persistencia.incluir(consultasTO);
    }

    @Override
    public ConsultasTO consultar(ConsultasTO consultasTO) {
        return this.persistencia.consultar(consultasTO);
    }

    @Override
    public List<ConsultasTO> listar(ConsultasTO consultasTO) {
        return this.persistencia.listar(consultasTO);
    }

}
