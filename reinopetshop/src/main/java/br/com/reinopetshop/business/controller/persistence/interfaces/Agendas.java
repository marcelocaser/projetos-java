package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.AgendasTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Agendas {

    public void alterar(AgendasTO agendasTO);

    public void excluir(AgendasTO agendasTO);

    public void incluir(AgendasTO agendasTO);

    public AgendasTO consultar(AgendasTO agendasTO);

    public List<AgendasTO> listar(AgendasTO agendasTO);

    public List<AgendasTO> listar();

    public List<AgendasTO> listarBanhoPorDataHora(Date data, Date hora);

    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal);

    public List<AgendasTO> listarPorPeriodo(Date dataInicial, Date dataFinal, Integer maximoDeAgendamentos);

    public List<AgendasTO> listarUltimosCompromissos(Integer maximoDeAgendamentos);

}
