package br.com.portalweb.web.controller.bean;

import br.com.core.util.RestUtil;
import br.com.portalweb.web.controller.IMEI;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class IMEIBean {

    private static final String urlIMEI = "http://www.imei.info/api/checkimei/";
    private static final String urlWS = "http://localhost:8084/portalweb/ws/teste/";

    public void consultaIMEI() {
        IMEI i = new IMEI();
        i.setLogin("marcelocaser");
        i.setImei("353335069650565");
        i.setPassword("mcp202728");
        //i = (IMEI) RestUtil.postBuscaRetornoRestSpring(i, urlIMEI);
        //System.out.println("IMEI - " + i.getImei() + " - " + i.getError());
        System.out.println(" " + RestUtil.postBuscaRetornoRestSpring("imei=356234062217513&login=marcelocaser&Password=mcp202728", urlIMEI));
        //URI uri = (URI) RestUtil.postBuscaRetornoRestSpring(i, urlWS);
        //System.out.println("Location : " + uri.toASCIIString());
    }

}
