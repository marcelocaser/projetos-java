package br.com.core.resource;

import java.util.Locale;
import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;
import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

/**
 *
 * @author marce
 */
public class MessageSourcePropertyResolver extends SpringBeanFacesELResolver {
    
    @Override
    public Object getValue(ELContext elContext, Object base, Object property)
            throws ELException {

        if (base instanceof MessageSource && property instanceof String) {
            String result = ((MessageSource) base).getMessage(
                    (String) property, null, getLocale());

            if (null != result) {
                elContext.setPropertyResolved(true);
            }

            return result;
        }

        return super.getValue(elContext, base, property);
    }

    private Locale getLocale() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getRequestLocale();
    }

}
