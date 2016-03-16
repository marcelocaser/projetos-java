package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String DATA_HORA_BR_SEGUNDOS = "dd/MM/yyyy HH:mm:ss";
    public static final String DATA_HORA_EN_SEGUNDOS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_HORA_BR_MINUTOS = "dd/MM/yyyy HH:mm";
    public static final String DATA_HORA_EN_MINUTOS = "yyyy-MM-dd HH:mm";
    public static final String DATA_BR_PADRAO_BARRAS = "dd/MM/yyyy";
    public static final String DATA_EN_PADRAO = "yyyyMMdd";
    public static final String DATA_EN_PADRAO_TRAÇO = "yyyy-MM-dd";
    public static final String HORA = "HH:mm:ss";
    public static final String HORA_ORACLE_24H = "HH24:MM:SS";
    public static final Locale pt_BR = new Locale("pt", "BR");
    public static final String DEFAULT_PATTERN = "dd/MM/yyyy";
    public static final DateFormatSymbols SYMBOL = new DateFormatSymbols(pt_BR);

    private DateUtil() {
    }

    /**
     * Converte uma String em um objeto data, de acordo com o pattern
     *
     * @param data
     * @param pattern
     * @return
     * @throws br.com.core.exception.NegocioException
     */
    public static Date getData(String data, String pattern) throws NegocioException {
        Date getData = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, SYMBOL);
        try {
            getData = sdf.parse(data);
        } catch (ParseException ex) {
            throw new NegocioException("Erro ao converter data. Data: '" + data + "' Pattern: '" + pattern + "'");
        }
        return getData;
    }

    public static Date getHora(String data, String pattern) throws NegocioException {
        Date getData = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, SYMBOL);
        try {
            getData = sdf.parse(data);
        } catch (ParseException ex) {
            throw new NegocioException("Erro ao converter Hora. Hora: '" + data + "' Pattern: '" + pattern + "'");
        }
        return getData;
    }

    /**
     * Converte uma string em Date utilizando o pattern DEFAULT
     *
     * @param data
     * @return
     */
    public static Date getData(String data) throws NegocioException {
        return getData(data, DEFAULT_PATTERN);
    }

    /**
     * Método que formata uma data em texto no padrão dd/MM/aaaa.
     *
     * @param data O objeto Date.
     * @param formato
     * @return Uma String formatada ou null caso a data não seja válida.
     */
    public static String getData(Date data, String formato) {
        return formataData(data, formato);
    }

    /**
     * Método que formata um texto em data no padrão dd/MM/aaaa HH:mm:ss.
     *
     * @param data O texto da data.
     * @param formato
     * @return Um objeto Date ou null caso não consiga fazer o parser.
     */
    public static Date getDataHora(String data, String formato) {
        return formataData(data, formato);
    }

    /**
     * Método que formata uma data em texto no padrão dd/MM/aaaa HH:mm:ss.
     *
     * @param data O objeto Date.
     * @param formato
     * @return Uma String formatada ou null caso a data não seja válida.
     */
    public static String getDataHora(Date data, String formato) {
        return formataData(data, formato);
    }

    /**
     * Método que formata a data.
     *
     * @param data A data do tipo Date.
     * @param formato O formato desejado.
     * @return A data formatada ou null se tiver erro.
     */
    public static String formataData(Date data, String formato) {
        try {
            return new SimpleDateFormat(formato).format(data);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Método que formata a data.
     *
     * @param data A data em formato string.
     * @param formato O formato desejado.
     * @return A data como objeto ou null se tiver erro.
     */
    public static Date formataData(String data, String formato) {
        try {
            return new SimpleDateFormat(formato).parse(data);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Formata data no padrão pt_BR <code>dd/MM/yyyy</code>.
     *
     * @param date
     * @param formato
     * @return Data formatada.
     */
    public static String dataFormatter(Date date, String formato) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * Formata data no padrão pt_BR <code>dd/MM/yyyy</code>.
     *
     * @param date
     * @return Data formatada dd/MM/yyyy.
     */
    public static String dataFormatter(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * Método que retorna a diferença em dias entre duas datas.
     *
     * @param dataInicial É a data inicial que será comparada.
     * @param dataFinal É a data final que será comparada.
     * @return Diferença em dias entre as duas datas.
     */
    public static long getDiasDiferenca(Date dataInicial, Date dataFinal) {
        Calendar di = Calendar.getInstance();
        Calendar df = Calendar.getInstance();
        di.setTime(dataInicial);
        df.setTime(dataFinal);

        long diferenca = df.getTimeInMillis() - di.getTimeInMillis();
        // Quantidade de milissegundos em um dia
        int tempoDia = 1000 * 60 * 60 * 24;
        long diasDiferenca = diferenca / tempoDia;
        return diasDiferenca;
    }

    /**
     * Retorna a data adicionando o dia, mês ou ano.
     *
     * @param date É a data que será modificada.
     * @param constanteCalendar Constante da classe Calendar que representa o
     * dia, mês ou ano.
     * @param diaMesAno Quantidade em dias, mês ou ano.
     * @param trataDomingo Informa se deseja tratar o domingo como dia útil ou não.
     * @return Uma data.
     */
    public static Date adicionaDiaMesAno(Date date, final int constanteCalendar, int diaMesAno, boolean trataDomingo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(constanteCalendar, diaMesAno);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && trataDomingo) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        return calendar.getTime();

    }

    /**
     * Retorna uma data do tipo LocalDate.
     *
     * @param data Uma data do tipo java.util.Date.
     * @return Data do tipo LocalDate.
     */
    public static LocalDate toLocalDate(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        //trata o último dia do mês de fevereiro e ano bissexto.
        if (calendar.get(Calendar.MONTH) == 2 && calendar.get(Calendar.DAY_OF_MONTH) >= 29) {
            calendar.set(Calendar.DAY_OF_MONTH, 28);
        }
        int mes = calendar.get(Calendar.MONTH) + 1;
        LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), mes, calendar.get(Calendar.DAY_OF_MONTH));
        //trata ano bissexto.
        if (localDate.isLeapYear() && calendar.get(Calendar.DAY_OF_MONTH) == 29) {
            calendar.set(Calendar.DAY_OF_MONTH, 29);
            localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        return localDate;
    }

    /**
     * Converte do data do tipo LocalDate para o tipo java.util.Date.
     *
     * @param date Uma data do tipo LocalDate.
     * @return Data do tipo java.util.Date.
     */
    public static Date toDate(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        return calendar.getTime();
    }

    /**
     * Verifica se uma data é posterior a outra data
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static boolean after(Date dataInicial, Date dataFinal) {
        Calendar di = Calendar.getInstance();
        Calendar df = Calendar.getInstance();
        di.setTime(dataInicial);
        df.setTime(dataFinal);
        return di.after(df);
    }

    /**
     * Verifica se uma data é anterior a outra data
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static boolean before(Date dataInicial, Date dataFinal) {
        Calendar di = Calendar.getInstance();
        Calendar df = Calendar.getInstance();
        di.setTime(dataInicial);
        df.setTime(dataFinal);
        return di.before(df);
    }

    public static long getAtraso(Date dataInicial, Date dataFinal) {
        Calendar di = Calendar.getInstance();
        Calendar df = Calendar.getInstance();
        di.setTime(dataInicial);
        df.setTime(dataFinal);

        if (di.before(df)) {
            return 0;
        }

        long diferenca = df.getTimeInMillis() - di.getTimeInMillis();
        // Quantidade de milissegundos em um dia
        int tempoDia = 1000 * 60 * 60 * 24;
        long diasDiferenca = diferenca / tempoDia;
        return diasDiferenca;
    }

    public static Date toDataOuHora(String dataHora, String formato) throws NegocioException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        LocalTime time;
        LocalDate date;
        LocalDateTime dateTime;
        int mes;
        if (dataHora.contains("/") || dataHora.contains("-")) {
            //data e hora
            if (dataHora.contains(":") || dataHora.contains("PM") || dataHora.contains("AM")) {
                dateTime = LocalDateTime.of(new Integer(dataHora.substring(6, 10)), new Integer(dataHora.substring(3, 5)), new Integer(dataHora.substring(0, 2)), new Integer(dataHora.substring(11, 13)), new Integer(dataHora.substring(14, 16)), new Integer(dataHora.substring(17, 19)));
                mes = dateTime.getMonthValue() - 1;
                calendar.set(dateTime.getYear(), mes, dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
            } else {
                //somente data
                date = LocalDate.of(new Integer(dataHora.substring(6, 10)), new Integer(dataHora.substring(3, 5)), new Integer(dataHora.substring(0, 2)));
                mes = date.getMonthValue() - 1;
                calendar.set(date.getYear(), mes, date.getDayOfMonth());
            }
        } else {
            //somete hora
            time = LocalTime.of(new Integer(dataHora.substring(0, 2)), new Integer(dataHora.substring(3, 5)), new Integer(dataHora.substring(6, 8)));
            calendar.set(time.getHour(), time.getMinute(), time.getSecond());
        }
        //System.out.println("Data/Hora: " + dateFormat.format(calendar.getTime()));
        dataHora = dateFormat.format(calendar.getTime());
        try {
            return dateFormat.parse(dataHora);
        } catch (ParseException ex) {
            throw new NegocioException("Não foi possível converter para Data ou Hora de acordo com os parâmetros informados.");
        }
    }

    public static Date getUltimoDiaMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getPrimeiroDiaMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
