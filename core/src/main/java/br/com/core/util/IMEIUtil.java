package br.com.core.util;

/**
 *
 * @author marce
 */
public class IMEIUtil {

    private IMEIUtil() {
    }

    public static boolean validar(String numero) {
        int soma = 0;
        for (int i = 0; i < 15; i++) {
            soma = soma
                    + calculoDoDigito((Integer.parseInt(String
                                    .valueOf(numero.charAt(i)))), (i + 1) % 2 == 0);
        }
        return soma % 10 == 0;
    }

    private static int calculoDoDigito(int valorSemDigito, boolean indexKey) {
        if (indexKey) {
            int number = valorSemDigito * 2;
            while (number > 9) {
                number = Integer.parseInt(String.valueOf(String.valueOf(number)
                        .charAt(0)))
                        + Integer.parseInt(String.valueOf(String
                                        .valueOf(number).charAt(1)));
            }
            return number;
        } else {
            return valorSemDigito;
        }
    }

}
