package br.com.qbe.ws.seguro.business.controller;

import br.com.qbe.bean.ApolicesEnvios;
import br.com.qbe.bean.ApolicesStatus;
import br.com.qbe.bean.ApolicesStatus.Apolice;
import br.com.qbe.bean.Cancelamentos;
import br.com.qbe.bean.Cancelamentos.Cancelamento;
import br.com.qbe.entity.ApolicesTO;
import br.com.qbe.ws.seguro.business.controller.business.ApolicesBO;
import br.com.qbe.ws.seguro.business.controller.business.CancelamentosBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marce
 */
@RestController
public class QBEWSController {

    @Autowired
    ApolicesBO apolicesNegocio;
    
    @Autowired
    CancelamentosBO cancelamentosNegocio;
    
    @RequestMapping(value = "/enviaApolicesClientes.json", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApolicesEnvios enviaApolices() {
        ApolicesTO apolicesTO = new ApolicesTO();
        ApolicesEnvios apolices = new ApolicesEnvios();
        apolices.setApolices(apolicesNegocio.listar(apolicesTO));
        return apolices;
    }

    @RequestMapping(value = "/recebeStatusEnvio.json", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApolicesStatus situacaoApolices() {
        ApolicesStatus apolices = new ApolicesStatus();
        Apolice ap = apolices.new Apolice();
        ap.setApolice("78123789");
        ap.setStatus("1");
        ap.setTexto("Enviado com sucesso!");
        apolices.addApolices(ap);
        ap.setApolice("2631261");
        ap.setStatus("0");
        ap.setTexto("Erro ao receber!");
        apolices.addApolices(ap);
        return apolices;
    }

    @RequestMapping(value = "/cancelaApolices.json", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Cancelamentos cancelaApolices() {
        Cancelamentos cancelamentos = new Cancelamentos();
        Cancelamento c = cancelamentos.new Cancelamento();
        c.setCnpjCpf("12982139812");
        c.setDataCancelamento("2016-05-03");
        c.setNumeroBilhete("2311122321");
        c.setTipoArquivo("4");
        c.setTipoCancelamento("1");
        cancelamentos.addCancelamentos(c);
        c.setCnpjCpf("42142134");
        c.setDataCancelamento("2016-03-03");
        c.setNumeroBilhete("12312");
        c.setTipoArquivo("4");
        c.setTipoCancelamento("1");
        cancelamentos.addCancelamentos(c);
        return cancelamentos;
    }

}
