package br.com.core.controller.business;

import br.com.core.entity.MensagensTO;
import br.com.core.persistence.interfaces.Mensagens;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * @author marce
 */
public class MensagensBO extends AbstractMessageSource implements ResourceLoaderAware {

    @Autowired
    private Mensagens persistencia;

    private ResourceLoader resourceLoader;
    private final Map<String, Map<String, String>> properties = new HashMap<>();

    public MensagensBO() {
        reload();
    }

    public MensagensBO(Mensagens persistencia) {
        this.persistencia = persistencia;
        reload();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }

    public String getText(String code, Locale locale) {
        Map<String, String> localized = properties.get(code);
        String textForCurrentLanguage = null;
        if (localized != null) {
            textForCurrentLanguage = localized.get(locale.getLanguage());
            if (textForCurrentLanguage == null) {
                textForCurrentLanguage = localized.get(Locale.ENGLISH.getLanguage());
            }
        }
        if (textForCurrentLanguage == null) {
            //Check parent message
            logger.debug("Fallback to properties message");
            try {
                textForCurrentLanguage = getParentMessageSource().getMessage(code, null, locale);
            } catch (Exception e) {
                logger.error("Cannot find message with code: " + code);
            }
        }
        return textForCurrentLanguage != null ? textForCurrentLanguage : code;
    }

    public void reload() {
        properties.clear();
        properties.putAll(loadTexts());
    }

    protected Map<String, Map<String, String>> loadTexts() {
        Map<String, Map<String, String>> m = new HashMap<>();
        List<MensagensTO> texts = persistencia.listar();
        for (MensagensTO text : texts) {
            Map<String, String> v = new HashMap<>();
            switch (text.getIdIdioma().getLocal()) {
                case Mensagens.en:
                    v.put(text.getIdIdioma().getLocal(), text.getMensagem());
                default:
                    v.put(text.getIdIdioma().getLocal(), text.getMensagem());
            }
            m.put(text.getChave(), v);
        }
        return m;
    }

}
