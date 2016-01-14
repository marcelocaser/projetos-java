package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <b>Classe:</b> ResourceBundleUtil.java <br>
 * <b>Descrição:</b> <br>
 *
 * <b>Projeto:</b> flavios-core <br>
 * <b>Pacote:</b> br.com.flavios.core.util <br>
 * <b>Empresa:</b> Flávios Calçados e Esportes <br>
 *
 * @author marcelocaser
 * @version Revision: $$ Date: 02/07/2013
 */
public class ResourceBundleUtil {

    private static ResourceBundle resource;

    /**
     * Método que cria uma instância de bundle de um arquivo .properties.
     *
     * @param bundle Local onde está o arquivo .properties.
     * @return Valor de acordo com a chave passada.
     * @throws NegocioException
     */
    private ResourceBundleUtil(String bundle) throws NegocioException {
        try {
            resource = PropertyResourceBundle.getBundle(bundle);

            if (resource == null) {
                throw new NegocioException("Não foi encontrado o arquivo " + bundle + ".properties dentro do classpath");
            }
        } catch (MissingResourceException e) {
            throw new NegocioException("Não foi possível encontrar pacote para o nome da base " + bundle + ", locale pt_BR.");
        }
    }

    /**
     * Método que cria uma nova instância da classe.
     *
     * @param bundle É o arquivo .properties.
     * @return ResourceBundleUtil
     * @throws NegocioException
     */
    public static ResourceBundleUtil newInstance(String bundle) throws NegocioException {
        return new ResourceBundleUtil(bundle);
    }

    /**
     * Método que busca e retorna o valor de uma chave passada por parâmetro.
     *
     * @param key É a chave de busca.
     * @return Valor de acordo com a chave passada.
     * @throws NegocioException
     */
    public String getKey(String key) throws NegocioException {
        try {
            return resource.getString(key);
        } catch (MissingResourceException e) {
            throw new NegocioException("Não é possível encontrar recursos para java.util.PropertyResourceBundle, chave " + key + ".");
        }
    }
}
