package br.com.core.controller.business.interfaces;

import br.com.core.entity.SistemasConfiguracoesTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface SistemasConfiguracoes {

    public void alterar(SistemasConfiguracoesTO sistemasConfiguracoesTO);

    public void excluir(SistemasConfiguracoesTO sistemasConfiguracoesTO);

    public void incluir(SistemasConfiguracoesTO sistemasConfiguracoesTO);

    public SistemasConfiguracoesTO consultar(SistemasConfiguracoesTO sistemasConfiguracoesTO);

    public List<SistemasConfiguracoesTO> listar(SistemasConfiguracoesTO sistemasConfiguracoesTO);

}
