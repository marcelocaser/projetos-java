package br.com.core.util;

import br.com.core.exception.UnknownResourceException;

/**
 *
 * @author marcelocaser
 */
public class CEPUtil {
    
    public static final String SUCESSO = "sucesso";
    public static final String ERRO = "erro";

    private CEPUtil() {
    }

    public static String isCEPValido(String cep) throws UnknownResourceException {
        if (cep != null && !cep.isEmpty()) {
            cep = cep.replaceAll("[A-Za-z.-]", "");
            if (cep.trim().length() == 8) {
                return cep.substring(0, 5).concat("-").concat(cep.substring(5, 8));
            }
        } else {
            throw new UnknownResourceException("CEP inválido.");
        }
        return null;
    }

}
