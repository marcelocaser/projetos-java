package br.com.reinopetshop.business.controller.persistence.interfaces;

import br.com.core.entity.AnimaisTO;
import java.util.List;

/**
 *
 * @author marce
 */
public interface Animais {

    public static final String CADASTRADO = "C";
    public static final String OBITO = "O";
    public static final String A_VENDA = "V";
    public static final String CONTROLE_DE_RACAO = "R";

    public void alterar(AnimaisTO animaisTO);

    public void excluir(AnimaisTO animaisTO);

    public void incluir(AnimaisTO animaisTO);

    public AnimaisTO consultar(AnimaisTO animaisTO);

    public List<AnimaisTO> listar(AnimaisTO animaisTO);

}
