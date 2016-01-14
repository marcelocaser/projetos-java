package br.com.core.persistence.interfaces;

import br.com.core.entity.EnderecosTO;
import java.util.List;

/**
 *
 * @author marcelocaser
 */
public interface Enderecos {
    
    public void alterar(EnderecosTO enderecosTO);
    
    public EnderecosTO buscarUltimoId(EnderecosTO enderecosTO);
    
    public Integer estatisticaTotalDeCEP();
    
    public void excluir(EnderecosTO enderecosTO);
    
    public void incluir(EnderecosTO enderecosTO);
    
    public EnderecosTO consultar(EnderecosTO enderecosTO);
    
    public EnderecosTO consultarPeloCEP(String cep);
    
    public List<EnderecosTO> listar(EnderecosTO enderecosTO);
    
}
