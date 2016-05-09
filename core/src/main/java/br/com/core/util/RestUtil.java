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
     * @deprecated - Usar o método getBuscaRetornoRestSpring();
     *
     */
    @Deprecated
    public static Object buscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlRest, object.getClass());
    }

    public static Object getBuscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlRest, object.getClass());
    }

    public static Object postBuscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(urlRest, object, object.getClass());
    }
    
    public static void putBuscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(urlRest, object);
    }
    
    public static void deleteBuscaRetornoRestSpring(Object object, String urlRest) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlRest, object);
    }

}
