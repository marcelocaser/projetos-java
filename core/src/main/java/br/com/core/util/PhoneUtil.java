package br.com.core.util;

import br.com.core.exception.NegocioException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelocaser
 */
public class PhoneUtil {

    private PhoneUtil() {
    }

    private static PhoneNumber getPhoneNumber(String numero, String regiao) throws NegocioException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber swissNumberProto = phoneUtil.parse(numero, regiao);
            if (isMobileNumber(swissNumberProto)) {
                return swissNumberProto;
            } else {
                return null;
            }
        } catch (NumberParseException ex) {
            Logger.getLogger(PhoneUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Número de telefone inválido.");
        }
    }

    public static String formataNumeroPadraoE164(String numero, String regiao) throws NegocioException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        if (getPhoneNumber(numero, regiao) != null) {
            return phoneUtil.format(getPhoneNumber(numero, regiao), PhoneNumberUtil.PhoneNumberFormat.RFC3966);
        }
        return null;
    }

    public static boolean isMobileNumber(PhoneNumber phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        return phoneUtil.getNumberType(phoneNumber) == PhoneNumberUtil.PhoneNumberType.MOBILE;
    }

}
