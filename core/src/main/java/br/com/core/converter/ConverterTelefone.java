package br.com.core.converter;

import br.com.core.util.TextUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author marcelo
 */
//@FacesConverter(forClass = ConverterTelefone.class, value = "ConverterTelefone")
public class ConverterTelefone implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.trim().isEmpty()) {
            switch (string.length()) {
                case 8:
                    return TextUtil.formataMascara("####-####", string);
                case 9:
                    return TextUtil.formataMascara("# ####-####", string);
                case 10:
                    return TextUtil.formataMascara("(###) ####-####", string);
                case 11:
                    return TextUtil.formataMascara("(###) # ####-####", string);
                default:
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String telefone = "";
        if (o != null && o instanceof String) {
            switch (o.toString().length()) {
                case 8:
                    telefone = TextUtil.formataMascara("####-####", (String) o);
                    break;
                case 9:
                    telefone = TextUtil.formataMascara("# ####-####", (String) o);
                    break;
                case 10:
                    telefone = TextUtil.formataMascara("(0##) ####-####", (String) o);
                    break;
                case 11:
                    telefone = TextUtil.formataMascara("(0##) # ####-####", (String) o);
                    break;
                default:
            }
        }
        return telefone;
    }

}
