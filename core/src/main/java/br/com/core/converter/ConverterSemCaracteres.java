package br.com.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author marce
 */
public class ConverterSemCaracteres implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.trim().isEmpty()) {
            return string.replaceAll("[ )(./-meses]", "");
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof String) {
            return (String) o;
        }
        return null;
    }
    
    
}
