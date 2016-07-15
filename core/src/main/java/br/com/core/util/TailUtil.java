package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author marce
 */
public class TailUtil {

    private TailUtil() {

    }

    static long sleepTime = 1000;

    public static String tail(String[] args) throws NegocioException {

        try {
            if (args.length > 0) {

                if (args.length > 1) {
                    sleepTime = Long.parseLong(args[1]) * 1000;
                }

                BufferedReader input = new BufferedReader(new FileReader(args[0]));
                String currentLine = null;
                StringBuilder sb = new StringBuilder();

                while (true) {

                    if ((currentLine = input.readLine()) != null) {
                        sb.append(currentLine).append(System.lineSeparator());
                        continue;
                    }

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    
                    input.close();
                    
                    return sb.toString();

                }
            } else {
                return ("Missing parameter!\nUsage: tail fileName [updateTime (Seconds. default to 1 second)]");
            }
        } catch (FileNotFoundException ex) {
            throw new NegocioException("arquivoNaoEncontrado");
        } catch (IOException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return null;
    }

}
