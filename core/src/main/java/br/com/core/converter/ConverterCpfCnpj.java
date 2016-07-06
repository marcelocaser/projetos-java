package br.com.core.converter;

import br.com.core.util.TextUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author marce
 */
public class ConverterCpfCnpj implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.trim().isEmpty()) {
            switch (string.length()) {
                case 11:
                    return TextUtil.formatarCPF(string);
                case 14:
                    return TextUtil.formatarCNPJ(string);
                default:
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String cpfCnpf = "";
        if (o != null && o instanceof String) {
            switch (o.toString().length()) {
                case 11:
                    cpfCnpf = TextUtil.formatarCPF((String) o);
                    break;
                case 14:
                    cpfCnpf = TextUtil.formatarCNPJ((String) o);
                    break;
                default:
            }
        }
        return cpfCnpf;
    }

}
