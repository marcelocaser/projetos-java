package br.com.core.persistence.interfaces;

import br.com.core.entity.ConsultasTO;
import br.com.core.entity.UsuariosTO;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Consultas {

    public void alterar(ConsultasTO consultasTO);
    
    public List<ConsultasTO> consultarEstatisticaCEPConsultados(UsuariosTO usuariosTO, Integer[] range);

    public Integer estatisticaTotalDeCEPDesdeCadastro(UsuariosTO usuariosTO);
    
    public void excluir(ConsultasTO consultasTO);

    public void incluir(ConsultasTO consultasTO);

    public ConsultasTO consultar(ConsultasTO consultasTO);

    public List<ConsultasTO> listar(ConsultasTO consultasTO);

}
