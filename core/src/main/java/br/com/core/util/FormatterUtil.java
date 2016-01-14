package br.com.core.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
* <p><b>Interface   :</b> Formatter.java
* <p><b>Descrição:</b> <<Descreva o OBJETIVO da INTERFACE>>
*
* <p><b>Projeto  :</b> flavios-intelrisk 
* <p><b>Pacote   :</b> br.com.flavios.constants
* <p><b>Empresa  :</b> Flávios Calçados e Esportes 
*
* @author     : fabiooliveira
* @version    : Revision: Date: 23/01/2014
*/
public interface FormatterUtil {

    Locale LOCALE = new Locale("pt","BR");
    
    DecimalFormatSymbols SYMBOLS = new DecimalFormatSymbols(LOCALE);
    
    DecimalFormat DECIMAL_MOEDA = new DecimalFormat("###,###,###,###.00", SYMBOLS);
    
    DecimalFormat DECIMAL_PERCENT = new DecimalFormat("###,###,###,###.00 '%'", SYMBOLS);
    
}
