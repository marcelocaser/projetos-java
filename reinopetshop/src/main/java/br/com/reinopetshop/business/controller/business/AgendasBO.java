package br.com.reinopetshop.business.controller.business;

import br.com.core.entity.AgendasTO;
import br.com.core.util.DateUtil;
import br.com.reinopetshop.business.controller.persistence.interfaces.Agendas;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marce
 */
@Service
public class AgendasBO {

    @Autowired
    Agendas persistencia;

    public void alterar(AgendasTO agendasTO) {
        antesDeAlterar(agendasTO);
        this.persistencia.alterar(agendasTO);
    }

    private void antesDeAlterar(AgendasTO agendasTO) {
        agendasTO.setAlteracao(new Date());
    }

    private void antesDeExcluir(AgendasTO agendasTO) {
        agendasTO.setExclusao(new Date());
    }

    public Date dataEHora(Date data, Date hora) {
        String soData = DateUtil.formataData(data, DateUtil.DATA_BR_PADRAO_BARRAS);
        String soHora = DateUtil.formataData(hora, DateUtil.HORA);
        if (soData != null && soHora != null) {
            String dataEHora = soData.concat(" ").concat(soHora);
            Date dataComHora = DateUtil.formataData(dataEHora, DateUtil.DATA_HORA_BR_SEGUNDOS);
            return dataComHora;
        }
        return null;
    }

    public void excluir(AgendasTO agendasTO) {
        antesDeExcluir(agendasTO);
        this.persistencia.alterar(agendasTO);

    }

    public void incluir(AgendasTO agendasTO) {
        antesDeAlterar(agendasTO);
        this.persistencia.incluir(agendasTO);
    }

    public AgendasTO consultar(AgendasTO agendasTO) {
        return this.persistencia.consultar(agendasTO);
    }

    public List<AgendasTO> listar(AgendasTO agendasTO) {
        return this.persistencia.listar(agendasTO);
    }

    public List<AgendasTO> listar() {
        return this.persistencia.listar();
    }

    public List<AgendasTO> listarBanhoPorDataHora(Date data, Date hora) {
        return this.persistencia.listarBanhoPorDataHora(data, hora);
    }

    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal) {
        return this.persistencia.listarPorPeriodo(dataInicial, dataFinal);
    }

    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal, Integer maximoDeAgendamentos) {
        return this.persistencia.listarPorPeriodo(dataInicial, dataFinal, maximoDeAgendamentos);
    }

    public List<AgendasTO> listarUltimosCompromissos(Integer maximoDeAgendamentos) {
        return this.persistencia.listarUltimosCompromissos(maximoDeAgendamentos);
    }

}
