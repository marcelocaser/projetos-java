package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcelo
 */
public class ContaTelefonicaUtil {

    private static List<String> conteudoArquivo;
    private static HashMap<String, HashMap> linhas;
    public static final String TIPO_00 = "00";
    public static final String TIPO_10 = "10";
    public static final String TIPO_20 = "20";
    public static final String TIPO_30 = "30";
    public static final String TIPO_40 = "40";
    public static final String TIPO_50 = "50";
    public static final String TIPO_60 = "60";
    public static final String TIPO_70 = "70";
    public static final String TIPO_80 = "80";
    public static final String TIPO_90 = "90";
    public static final String TIPO_99 = "99";
    private static int contatorTipo00;
    private static int contatorTipo10;
    private static int contatorTipo20;
    private static int contatorTipo30;
    private static int contatorTipo40;
    private static int contatorTipo50;
    private static int contatorTipo60;
    private static int contatorTipo70;
    private static int contatorTipo90;
    private static int contatorTipo99;

    private ContaTelefonicaUtil() {

    }

    public static HashMap<String, HashMap> lerArquivo(File file) throws NegocioException {
        linhas = new HashMap<>();
        conteudoArquivo = new ArrayList<>();
        if (file != null) {
            conteudoArquivo = FileUtil.readFiles(file);
            lerDados();
        }
        return validadaQuantidaDeRegistros() ? linhas : null;
    }

    private static boolean validadaQuantidaDeRegistros() throws NegocioException {
        for (Map.Entry<String, HashMap> linha : linhas.entrySet()) {
            if (linha.getKey().equals(TIPO_99)) {
                HashMap value = linha.getValue();
                for (Iterator iterator = value.values().iterator(); iterator.hasNext();) {
                    Object object = iterator.next();
                    HashMap<Integer, String> hashMap = (HashMap) object;
                    /*System.out.println(": " + hashMap.get(1));
                     System.out.println(": " + hashMap.get(2));*/
                    for (Map.Entry<Integer, String> entrySet : hashMap.entrySet()) {
                        Integer key1 = entrySet.getKey();
                        String value1 = entrySet.getValue();
                        //System.out.println("key1: " + key1 + " value1: " + value1);
                        switch (entrySet.getKey()) {
                            case 9: //Quantidade de TOTAL de Registros
                                int totalGeral = contatorTipo10 + contatorTipo20 + contatorTipo30
                                        + contatorTipo40 + contatorTipo50 + contatorTipo60 + contatorTipo70;
                                //System.out.println("Total: " + totalGeral);
                                break;
                            case 11: //Quantidade de Registros tipo 10
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo10) {
                                    throw new NegocioException("Total de linha tipo 10 esperado: " + entrySet.getValue() + " informado: " + contatorTipo10);
                                }
                                break;
                            case 12: //Quantidade de Registros tipo 20
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo20) {
                                    throw new NegocioException("Total de linha tipo 20 esperado: " + entrySet.getValue() + " informado: " + contatorTipo20);
                                }
                                break;
                            case 14: //Quantidade de Registros tipo 30
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo30) {
                                    throw new NegocioException("Total de linha tipo 30 esperado: " + entrySet.getValue() + " informado: " + contatorTipo30);
                                }
                                break;
                            case 16: //Quantidade de Registros tipo 40
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo40) {
                                    throw new NegocioException("Total de linha tipo 40 esperado: " + entrySet.getValue() + " informado: " + contatorTipo40);
                                }
                                break;
                            case 19: //Quantidade de Registros tipo 50
                                /*if (Integer.parseInt(entrySet.getValue()) != contatorTipo50) {
                                 throw new NegocioException("Total de linha tipo 50 esperado: " + entrySet.getValue() + " informado: " + contatorTipo50);
                                 }*/
                                break;
                            case 21: //Quantidade de Registros tipo 60
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo60) {
                                    throw new NegocioException("Total de linha tipo 60 esperado: " + entrySet.getValue() + " informado: " + contatorTipo60);
                                }
                                break;
                            case 24: //Quantidade de Registros tipo 70
                                if (Integer.parseInt(entrySet.getValue()) != contatorTipo70) {
                                    throw new NegocioException("Total de linha tipo 70 esperado: " + entrySet.getValue() + " informado: " + contatorTipo70);
                                }
                                break;
                            case 26: //Quantidade de Registros tipo 80
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static void lerDados() throws NegocioException {
        HashMap<Integer, HashMap> linhaTipo00 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo10 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo20 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo30 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo40 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo50 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo60 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo70 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo90 = new HashMap<>();
        HashMap<Integer, HashMap> linhaTipo99 = new HashMap<>();
        contatorTipo10 = 0;
        contatorTipo20 = 0;
        contatorTipo30 = 0;
        contatorTipo40 = 0;
        contatorTipo50 = 0;
        contatorTipo60 = 0;
        contatorTipo70 = 0;
        contatorTipo90 = 0;
        contatorTipo99 = 0;
        try {
            for (String conteudo : conteudoArquivo) {
                HashMap<Integer, String> linha;
                switch (conteudo.substring(0, 2)) {
                    case TIPO_00:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 61).trim());
                        linha.put(7, conteudo.substring(61, 69).trim());
                        linha.put(8, conteudo.substring(69, 72).trim());
                        linha.put(9, conteudo.substring(72, 87).trim());
                        linha.put(10, conteudo.substring(87, 102).trim());
                        linha.put(11, conteudo.substring(102, 104).trim());
                        linha.put(12, conteudo.substring(104, 119).trim());
                        linha.put(13, conteudo.substring(119, 149).trim());
                        linha.put(14, conteudo.substring(149, 164).trim());
                        linha.put(15, conteudo.substring(164, 168).trim());
                        linha.put(16, conteudo.substring(168, 184).trim());
                        linha.put(17, conteudo.substring(184, 234).trim());
                        linha.put(18, conteudo.substring(234, 236).trim());
                        linha.put(19, conteudo.substring(236, 256).trim());
                        linha.put(20, conteudo.substring(256, 260).trim());
                        linha.put(21, conteudo.substring(260, 264).trim());
                        linha.put(22, conteudo.substring(264, 274).trim());
                        linha.put(23, conteudo.substring(274, 309).trim());
                        linha.put(24, conteudo.substring(309, 324).trim());
                        linha.put(25, conteudo.substring(324, 349).trim());
                        linha.put(26, conteudo.substring(349, 350).trim());
                        linhaTipo00.put(contatorTipo00++, linha);
                        linhas.put(TIPO_00, linhaTipo00);
                        break;
                    case TIPO_10:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 83).trim());
                        linha.put(8, conteudo.substring(83, 99).trim());
                        linha.put(9, conteudo.substring(99, 103).trim());
                        linha.put(10, conteudo.substring(103, 111).trim());
                        linha.put(11, conteudo.substring(111, 119).trim());
                        linha.put(12, conteudo.substring(119, 128).trim());
                        linha.put(13, conteudo.substring(141, 150).trim());
                        linha.put(14, conteudo.substring(150, 165).trim());
                        linha.put(15, conteudo.substring(165, 178).trim());
                        linha.put(16, conteudo.substring(178, 191).trim());
                        linha.put(17, conteudo.substring(191, 193).trim());
                        linha.put(18, conteudo.substring(193, 198).trim());
                        linha.put(19, conteudo.substring(198, 202).trim());
                        linha.put(21, conteudo.substring(202, 210).trim());
                        linha.put(20, conteudo.substring(210, 324).trim());
                        linha.put(22, conteudo.substring(324, 349).trim());
                        linha.put(23, conteudo.substring(349, 350).trim());
                        linhaTipo10.put(contatorTipo10++, linha);
                        linhas.put(TIPO_10, linhaTipo10);
                        break;
                    case TIPO_20:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 94).trim());
                        linha.put(8, conteudo.substring(94, 99).trim());
                        linha.put(9, conteudo.substring(99, 114).trim());
                        linha.put(10, conteudo.substring(114, 116).trim());
                        linha.put(11, conteudo.substring(116, 146).trim());
                        linha.put(12, conteudo.substring(146, 151).trim());
                        linha.put(13, conteudo.substring(151, 159).trim());
                        linha.put(14, conteudo.substring(159, 169).trim());
                        linha.put(15, conteudo.substring(169, 174).trim());
                        linha.put(16, conteudo.substring(174, 189).trim());
                        linha.put(17, conteudo.substring(189, 191).trim());
                        linha.put(18, conteudo.substring(191, 221).trim());
                        linha.put(19, conteudo.substring(221, 226).trim());
                        linha.put(20, conteudo.substring(226, 234).trim());
                        linha.put(21, conteudo.substring(234, 244).trim());
                        linha.put(22, conteudo.substring(244, 249).trim());
                        linha.put(23, conteudo.substring(249, 264).trim());
                        linha.put(24, conteudo.substring(264, 266).trim());
                        linha.put(25, conteudo.substring(266, 296).trim());
                        linha.put(26, conteudo.substring(296, 301).trim());
                        linha.put(27, conteudo.substring(301, 309).trim());
                        linha.put(28, conteudo.substring(309, 319).trim());
                        linha.put(29, conteudo.substring(319, 324).trim());
                        linha.put(30, conteudo.substring(324, 349).trim());
                        linha.put(31, conteudo.substring(349, 350).trim());
                        linhaTipo20.put(contatorTipo20++, linha);
                        linhas.put(TIPO_20, linhaTipo20);
                        break;
                    case TIPO_30:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 83).trim());
                        linha.put(8, conteudo.substring(83, 99).trim());
                        linha.put(9, conteudo.substring(99, 107).trim());
                        linha.put(10, conteudo.substring(107, 112).trim());
                        linha.put(11, conteudo.substring(112, 137).trim());
                        linha.put(12, conteudo.substring(137, 139).trim());
                        linha.put(13, conteudo.substring(139, 141).trim());
                        linha.put(14, conteudo.substring(141, 143).trim());
                        linha.put(15, conteudo.substring(143, 163).trim());
                        linha.put(16, conteudo.substring(163, 180).trim());
                        linha.put(17, conteudo.substring(180, 185).trim());
                        linha.put(18, conteudo.substring(185, 188).trim());
                        linha.put(19, conteudo.substring(188, 195).trim());
                        linha.put(20, conteudo.substring(195, 198).trim());
                        linha.put(21, conteudo.substring(198, 201).trim());
                        linha.put(22, conteudo.substring(201, 226).trim());
                        linha.put(23, conteudo.substring(226, 232).trim());
                        linha.put(24, conteudo.substring(232, 237).trim());
                        linha.put(25, conteudo.substring(237, 250).trim());
                        linha.put(26, conteudo.substring(250, 265).trim());
                        linha.put(27, conteudo.substring(265, 266).trim());
                        linha.put(28, conteudo.substring(266, 278).trim());
                        linha.put(29, conteudo.substring(278, 279).trim());
                        linha.put(30, conteudo.substring(279, 280).trim());
                        linha.put(31, conteudo.substring(280, 295).trim());
                        linha.put(32, conteudo.substring(295, 297).trim());
                        linha.put(33, conteudo.substring(297, 324).trim());
                        linha.put(34, conteudo.substring(324, 349).trim());
                        linha.put(35, conteudo.substring(349, 350).trim());
                        linhaTipo30.put(contatorTipo30++, linha);
                        linhas.put(TIPO_30, linhaTipo30);
                        break;
                    case TIPO_40:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 83).trim());
                        linha.put(8, conteudo.substring(83, 99).trim());
                        linha.put(9, conteudo.substring(99, 107).trim());
                        linha.put(10, conteudo.substring(107, 109).trim());
                        linha.put(11, conteudo.substring(109, 126).trim());
                        linha.put(12, conteudo.substring(126, 131).trim());
                        linha.put(13, conteudo.substring(131, 134).trim());
                        linha.put(14, conteudo.substring(134, 140).trim());
                        linha.put(15, conteudo.substring(140, 142).trim());
                        linha.put(16, conteudo.substring(142, 148).trim());
                        linha.put(17, conteudo.substring(148, 151).trim());
                        linha.put(18, conteudo.substring(151, 154).trim());
                        linha.put(19, conteudo.substring(154, 179).trim());
                        linha.put(20, conteudo.substring(179, 192).trim());
                        linha.put(21, conteudo.substring(192, 207).trim());
                        linha.put(22, conteudo.substring(207, 208).trim());
                        linha.put(23, conteudo.substring(208, 220).trim());
                        linha.put(24, conteudo.substring(220, 324).trim());
                        linha.put(25, conteudo.substring(324, 349).trim());
                        linha.put(26, conteudo.substring(349, 350).trim());
                        linhaTipo40.put(contatorTipo40++, linha);
                        linhas.put(TIPO_40, linhaTipo40);
                        break;
                    case TIPO_50:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 94).trim());
                        linha.put(8, conteudo.substring(94, 95).trim());
                        linha.put(9, conteudo.substring(95, 98).trim());
                        linha.put(10, conteudo.substring(98, 101).trim());
                        linha.put(11, conteudo.substring(101, 126).trim());
                        linha.put(12, conteudo.substring(126, 139).trim());
                        linha.put(13, conteudo.substring(139, 140).trim());
                        linha.put(14, conteudo.substring(140, 152).trim());
                        linha.put(15, conteudo.substring(152, 157).trim());
                        linha.put(16, conteudo.substring(157, 158).trim());
                        linha.put(17, conteudo.substring(158, 171).trim());
                        linha.put(18, conteudo.substring(171, 179).trim());
                        linha.put(19, conteudo.substring(179, 185).trim());
                        linha.put(20, conteudo.substring(185, 193).trim());
                        linha.put(21, conteudo.substring(193, 199).trim());
                        linha.put(22, conteudo.substring(199, 324).trim());
                        linha.put(23, conteudo.substring(324, 349).trim());
                        linha.put(24, conteudo.substring(349, 350).trim());
                        linhaTipo50.put(contatorTipo50++, linha);
                        linhas.put(TIPO_50, linhaTipo50);
                        break;
                    case TIPO_60:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 94).trim());
                        linha.put(8, conteudo.substring(94, 95).trim());
                        linha.put(9, conteudo.substring(95, 103).trim());
                        linha.put(10, conteudo.substring(103, 111).trim());
                        linha.put(11, conteudo.substring(111, 114).trim());
                        linha.put(12, conteudo.substring(114, 129).trim());
                        linha.put(13, conteudo.substring(129, 141).trim());
                        linha.put(14, conteudo.substring(141, 153).trim());
                        linha.put(15, conteudo.substring(153, 155).trim());
                        linha.put(16, conteudo.substring(155, 158).trim());
                        linha.put(17, conteudo.substring(159, 161).trim());
                        linha.put(18, conteudo.substring(161, 186).trim());
                        linha.put(19, conteudo.substring(186, 191).trim());
                        linha.put(20, conteudo.substring(191, 216).trim());
                        linha.put(21, conteudo.substring(216, 229).trim());
                        linha.put(22, conteudo.substring(229, 244).trim());
                        linha.put(23, conteudo.substring(244, 245).trim());
                        linha.put(24, conteudo.substring(245, 257).trim());
                        linha.put(25, conteudo.substring(257, 324).trim());
                        linha.put(26, conteudo.substring(324, 349).trim());
                        linha.put(27, conteudo.substring(349, 350).trim());
                        linhaTipo60.put(contatorTipo60++, linha);
                        linhas.put(TIPO_60, linhaTipo60);
                        break;
                    case TIPO_70:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 94).trim());
                        linha.put(8, conteudo.substring(94, 95).trim());
                        linha.put(9, conteudo.substring(95, 98).trim());
                        linha.put(10, conteudo.substring(98, 101).trim());
                        linha.put(11, conteudo.substring(101, 141).trim());
                        linha.put(12, conteudo.substring(141, 154).trim());
                        linha.put(13, conteudo.substring(154, 159).trim());
                        linha.put(14, conteudo.substring(159, 160).trim());
                        linha.put(15, conteudo.substring(160, 173).trim());
                        linha.put(16, conteudo.substring(173, 181).trim());
                        linha.put(17, conteudo.substring(181, 187).trim());
                        linha.put(18, conteudo.substring(187, 195).trim());
                        linha.put(19, conteudo.substring(195, 201).trim());
                        linha.put(20, conteudo.substring(201, 324).trim());
                        linha.put(21, conteudo.substring(324, 349).trim());
                        linha.put(22, conteudo.substring(349, 350).trim());
                        linhaTipo70.put(contatorTipo70++, linha);
                        linhas.put(TIPO_70, linhaTipo70);
                        break;
                    case TIPO_80:
                        //Tipo 80 - Nota Fiscal
                        break;
                    case TIPO_90:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 78).trim());
                        linha.put(7, conteudo.substring(78, 94).trim());
                        linha.put(8, conteudo.substring(94, 95).trim());
                        linha.put(9, conteudo.substring(95, 98).trim());
                        linha.put(10, conteudo.substring(98, 101).trim());
                        linha.put(11, conteudo.substring(101, 141).trim());
                        linha.put(12, conteudo.substring(141, 154).trim());
                        linha.put(13, conteudo.substring(154, 159).trim());
                        linha.put(14, conteudo.substring(159, 160).trim());
                        linha.put(15, conteudo.substring(160, 173).trim());
                        linha.put(16, conteudo.substring(173, 181).trim());
                        linha.put(17, conteudo.substring(181, 187).trim());
                        linha.put(18, conteudo.substring(187, 195).trim());
                        linha.put(19, conteudo.substring(195, 201).trim());
                        linha.put(20, conteudo.substring(201, 324).trim());
                        linha.put(21, conteudo.substring(324, 349).trim());
                        linha.put(22, conteudo.substring(349, 350).trim());
                        linhaTipo90.put(contatorTipo90++, linha);
                        linhas.put(TIPO_90, linhaTipo90);
                        break;
                    case TIPO_99:
                        linha = new HashMap<>();
                        linha.put(1, conteudo.substring(0, 2).trim());
                        linha.put(2, conteudo.substring(2, 14).trim());
                        linha.put(3, conteudo.substring(14, 39).trim());
                        linha.put(4, conteudo.substring(39, 47).trim());
                        linha.put(5, conteudo.substring(47, 53).trim());
                        linha.put(6, conteudo.substring(53, 61).trim());
                        linha.put(7, conteudo.substring(61, 76).trim());
                        linha.put(8, conteudo.substring(76, 89).trim());
                        linha.put(9, conteudo.substring(89, 101).trim());
                        linha.put(10, conteudo.substring(101, 114).trim());
                        linha.put(11, conteudo.substring(114, 123).trim());
                        linha.put(12, conteudo.substring(123, 132).trim());
                        linha.put(13, conteudo.substring(132, 145).trim());
                        linha.put(14, conteudo.substring(145, 154).trim());
                        linha.put(15, conteudo.substring(154, 167).trim());
                        linha.put(16, conteudo.substring(167, 176).trim());
                        linha.put(17, conteudo.substring(176, 177).trim());
                        linha.put(18, conteudo.substring(177, 190).trim());
                        linha.put(19, conteudo.substring(190, 199).trim());
                        linha.put(20, conteudo.substring(199, 212).trim());
                        linha.put(21, conteudo.substring(212, 221).trim());
                        linha.put(22, conteudo.substring(221, 222).trim());
                        linha.put(23, conteudo.substring(222, 235).trim());
                        linha.put(24, conteudo.substring(235, 244).trim());
                        linha.put(25, conteudo.substring(244, 257).trim());
                        linha.put(26, conteudo.substring(257, 266).trim());
                        linha.put(27, conteudo.substring(266, 324).trim());
                        linha.put(28, conteudo.substring(324, 349).trim());
                        linha.put(29, conteudo.substring(349, 350).trim());
                        linhaTipo99.put(contatorTipo99++, linha);
                        linhas.put(TIPO_99, linhaTipo99);
                        break;
                    default:
                        throw new NegocioException("Erro ao ler o conteúdo do arquivo. Nenhum TIPO encontrado.");
                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            throw new NegocioException("Erro ao ler dados do arquivo....");
        } catch (NullPointerException ex) {
            throw new NegocioException("Arquivo comtém campo nulo, inválido");
        }
    }
}
