package br.com.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtil {

    /**
     * Método que faz o cálculo de arredondamento das parcelas de acordo com a
     * regra que hoje prevalece na Flávios.
     *
     * <p> Regra: (Valor * 20) + 0.5 / 20
     *
     * @param valor Valor que será arredondado.
     * @return Valor arredondado de acordo com a regra.
     */
    public static BigDecimal arredondamentoDeParcela(BigDecimal valor) {
        if (valor == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal twenty = new BigDecimal(20);
        BigDecimal result = new BigDecimal(valor.multiply(twenty)
                .add(new BigDecimal("0.5"))
                .toBigInteger()).divide(twenty);
        return result;
    }

    /**
     * Formata um número de acordo com a máscara #,## no padrão pt_BR.
     *
     * @param number É o número que será formatado.
     * @return Número formatado.
     */
    public static String decimalFormatter(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        return decimalFormat.format(number);
    }

    /**
     * Converte um número no padrão pt_BR para en_US.
     *
     * @param number É o número que será convertido. Válido apenas para o padrão
     * brasileiro (pt_BR).
     * @return Número no padrão en_US.
     */
    public static BigDecimal decimalFormatter(String number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            return new BigDecimal(decimalFormat.parse(number).toString());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Formata um número de acordo com a máscara #,###0.000 no padrão pt_BR.
     *
     * @param number É o número que será formatado.
     * @return Número formatado.
     */
    public static String decimalFormatterSmps(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###0.000", new DecimalFormatSymbols(new Locale("pt", "BR")));
        return decimalFormat.format(number);
    }

    /**
     * Método que faz a formatação de números com inteiros e frações.
     *
     * @param valor O valor a ser formatado.
     * @param inteiros O mínimo de inteiros, que serão completados com ZEROS se
     * preciso.
     * @param decimal O mínimo de decimais, que serão completados com ZEROS se
     * preciso.
     * @param grupo Se será colocado separador de grupo de milhar.
     * @return Uma String com o número formatado.
     * @see #formataNumero(double, int, int, boolean)
     */
    public static String formataNumero(String valor, int inteiros, int decimal, boolean grupo) {
        return formataNumero(Double.valueOf(valor), inteiros, decimal, grupo);
    }

    /**
     * Método que faz a formatação de números com inteiros e frações.
     *
     * @param valor O valor a ser formatado.
     * @param inteiros O mínimo de inteiros, que serão completados com ZEROS se
     * preciso.
     * @param decimal O mínimo de decimais, que serão completados com ZEROS se
     * preciso.
     * @param grupo Se será colocado separador de grupo de milhar.
     * @return Uma String com o número formatado.
     */
    public static String formataNumero(double valor, int inteiros, int decimal, boolean grupo) {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(inteiros);
        nf.setMinimumFractionDigits(decimal);
        nf.setMaximumFractionDigits(decimal);
        nf.setGroupingUsed(grupo);
        return nf.format(valor);
    }
}
