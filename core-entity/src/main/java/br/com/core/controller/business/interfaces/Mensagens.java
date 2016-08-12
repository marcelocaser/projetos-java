package br.com.core.controller.business.interfaces;

import br.com.core.entity.MensagensTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Mensagens {
    
    public static final String en = "en";
    public static final String pt_BR = "pt_BR";

    public List<MensagensTO> listar();

}
