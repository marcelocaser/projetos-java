package br.com.core.web.bean;

import br.com.core.exception.NegocioException;
import br.com.core.resource.ResourceServiceUtil;
import br.com.core.util.TailUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author marce
 */
@Component
@Scope
public class TerminalBean {

    public String consultar() {
        return "/app/terminal/terminalConsultar";
    }

    public String handleCommand(String command, String[] params) {

        try {
            switch (command) {
                case "tail":
                    return TailUtil.tail(params);
                default:
                    return ResourceServiceUtil.getMessageResourceString("terminalComandoNaoEncontrado", null) +": " + command;
            }
        } catch (NegocioException ex) {
            return ex.getMensagem();
        }
    }

}
