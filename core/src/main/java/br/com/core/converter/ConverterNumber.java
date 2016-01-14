package br.com.core.converter;

import br.com.core.util.NumberUtil;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author marcelo
 */
public class ConverterNumber implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.trim().isEmpty()) {
            return NumberUtil.decimalFormatter(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof String) {
            String value = o.toString().replace(",", ".").trim();
            BigDecimal valor = new BigDecimal(value);
            return "SALDO: R$ ".concat(NumberUtil.decimalFormatter(valor));
        }
        return null;
    }

}
