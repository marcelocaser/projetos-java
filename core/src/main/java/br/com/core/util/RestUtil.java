package br.com.core.util;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marcelocaser
 */
public class RestUtil {

    private RestUtil() {
    }

    /**
     * Busca retorno WebServices usando Spring FrameWork.
     *
     * @param object - Objeto que deseja alimentar.
     * @param urlRest - URL que será consumida.
     * @return - Objeto e seus respectivos valores.
     */
    public static Object buscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlRest, object.getClass());
    }

}
