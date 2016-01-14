package br.com.core.test;

import br.com.core.exception.NegocioException;
import br.com.core.util.ContaTelefonicaUtil;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            ContaTelefonicaUtil.lerArquivo(new File("C:\\Temp\\f_bill_14_9572040_1150472366_20150415.txt"));
        } catch (NegocioException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
