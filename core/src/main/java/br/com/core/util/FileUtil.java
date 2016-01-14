package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author alessandroguimaraes
 */
public class FileUtil {

    /**
     * Separador usando ponto e vírgula. <code>";"</code>.
     */
    public static final String pontoEVirgula = ";";
    /**
     * Separador usando ponto e vírgula mais espaço. <code>"; "</code>
     */
    public static final String pontoEVirgulaComEspaco = "; ";
    /**
     * Quebra de linha padrão windows. <code>\r\n</code>
     */
    public static final String quebraDeLinhaWindows = "\r\n";
    /**
     * Quebra de linha padrão linux. <code>\n</code>
     */
    public static final String quebraDeLinhaLinux = "\n";
    /**
     * Logs de erros.
     */
    public static StringBuilder stringBuilderErro = new StringBuilder("### ERROS ###").append("\n\n");
    /**
     * Logs de informações.
     */
    public static StringBuilder stringBuilderInfo = new StringBuilder("### INFORMAÇÕES ###").append("\n\n");

    private FileUtil() {
    }

    /**
     * Método que irá gravar dados dentro de um arquivo.
     *
     * @param file É o nome do arquivo.
     * @param content É um array de conteúdo que será gravado no arquivo.
     * @param append Informa se o conteúdo do arquivo será adicionado ao final
     * do arquivo ou se será criado novos registros.
     * @throws NegocioException
     */
    public static void createFile(File file, String content[], Boolean append) throws NegocioException {
        try {
            FileWriter fileWriter = new FileWriter(file, append);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String string : content) {
                if (string != null) {
                    printWriter.print(string);
                }
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            throw new NegocioException("Impossível gravar no arquivo!");
        }
    }

    /**
     * Método que irá gravar dados dentro de um arquivo.
     *
     * @param file É o nome do arquivo.
     * @param content É o conteudo que será gravado no arquivo.
     * @param append Informa se o conteúdo do arquivo será adicionado ao final
     * do arquivo ou se será criado novos registros.
     * @throws NegocioException
     */
    public static void createFile(File file, String content, Boolean append) throws NegocioException {
        try {
            FileWriter fileWriter = new FileWriter(file, append);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if (content != null) {
                printWriter.print(content);
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            throw new NegocioException("Impossível gravar no arquivo!");
        }
    }

    /**
     * Método que irá gravar dados dentro de um arquivo.
     *
     * @param file É o nome do arquivo.
     * @param object É o objeto que será gravado no arquivo.
     * @param append Informa se o conteúdo do arquivo será adicionado ao final
     * do arquivo ou se será criado novos registros.
     * @throws NegocioException
     */
    public static void createFile(File file, Object object, Boolean append) throws NegocioException {
        try {
            FileWriter fileWriter = new FileWriter(file, append);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Object conteudoDoArquivo : ObjectUtil.getValores(object)) {
                if (conteudoDoArquivo != null) {
                    printWriter.print(conteudoDoArquivo);
                }
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            throw new NegocioException("Impossível gravar no arquivo!");
        }
    }

    /**
     * Método responsável por criar um arquivo.
     *
     * @param fileName Nome do arquivo com o caminho e extensão.
     * @return Arquivo criado.
     * @throws NegocioException
     */
    public static File newFile(String fileName) throws NegocioException {
        try {
            File file = new File(fileName);

            return file;

        } catch (NullPointerException ex) {
            throw new NegocioException("Impossível criar o arquivo!");
        }
    }

    /**
     * Método responsável por abrir um arquivo.
     *
     * @param fileName Nome do arquivo com o caminho e extensão.
     * @return Arquivo existente.
     * @throws NegocioException
     */
    public static File openFile(String fileName) throws NegocioException {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                throw new NegocioException("Arquivo não encontrado!");
            }

            return file;

        } catch (NullPointerException ex) {
            throw new NegocioException("Impossível ler o arquivo!");
        }
    }

    /**
     * Método responsável por ler um arquivo.
     *
     * @param file É o caminho com o arquivo que será lido.
     * @return Conteúdo do arquivo.
     * @throws NegocioException
     */
    public static StringBuilder readFile(File file) throws NegocioException {
        try {
            InputStreamReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linha;
            StringBuilder builder = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null) {
                builder.append(linha).append("\n");
            }
            return builder;
        } catch (FileNotFoundException ex) {
            throw new NegocioException("Arquivo não encontrado!");
        } catch (Exception ex) {
            throw new NegocioException("Erro ao ler arquivo!");
        }
    }

    /**
     * Método responsável por ler um arquivo.
     *
     * @param file É o arquivo que será lido.
     * @param delimitador É o separador de campos/colunas.
     * @return Conteúdo do arquivo lido.
     * @throws NegocioException
     */
    public static String[] readFile(File file, String delimitador) throws NegocioException {

        String linha;
        String[] conteudo = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                linha = bufferedReader.readLine();
                conteudo = linha.split(delimitador);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            throw new NegocioException("Arquivo não encontrado!!");
        } catch (IOException ex) {
            throw new NegocioException("Impossível ler o arquivo!");
        }
        return conteudo;
    }

    public static List<String> readFiles(File file) throws NegocioException {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            return lines;
        } catch (IOException ex) {
            throw new NegocioException("Impossível ler o arquivo!");
        }
    }

    /**
     * Método recursivo que pega todos os arquivos e diretórios dentro de um
     * caminho específico.
     *
     * @param sourcePath É o caminho do diretório.
     * @param fileList É uma lista contendo todos os arquivo de um diretório.
     * @return Lista de arquivo de um diretório
     */
    public static List<File> getFilesToCopy(String sourcePath, List<File> fileList) {
        File[] files = new File(sourcePath).listFiles();
        for (File file : files) {
            fileList.add(file);
            if (file.listFiles() != null) {
                getFilesToCopy(file.getAbsolutePath(), fileList);
            }
        }
        return fileList;
    }

    public static List<File> getFiles(ServletContext context, String folder) {
        File[] files = new File(context.getRealPath("/" + folder + "/")).listFiles();
        List<File> fileList = new ArrayList<>();
        fileList.addAll(Arrays.asList(files));
        return fileList;
    }

    /**
     * Método que verifica se é um diretório.
     *
     * @param nameDirectory Nome do diretório.
     * @return Verdadeiro se existe ou falso caso contrário.
     */
    public static Boolean isDirectory(String nameDirectory) {
        File file = new File(nameDirectory);
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Método que copia um arquivo ou cria um diretório no destino especificado
     * no parâmetro.
     *
     * @param source É o arquivo de origem.
     * @param destination É o arquivo de desntino.
     */
    public static void copyFileOrDirectory(String source, String destination) {
        File fileOrigem = new File(source);
        File fileDestino = new File(fileOrigem.getAbsolutePath().replace(source, destination));
        if (fileOrigem.isDirectory()) {
            if (fileDestino.mkdirs()) {
                stringBuilderInfo.append("   - Diretório criado: ")
                        .append(fileDestino.getAbsolutePath())
                        .append("\n");
            }
        } else {
            try {
                Files.copy(fileOrigem.toPath(), fileDestino.toPath(),
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES);
                stringBuilderInfo.append("   + Arquivo copiado: ")
                        .append(fileDestino.getAbsolutePath())
                        .append("\n");
            } catch (IOException ex) {
                stringBuilderInfo.append("   * Erro ao copiar arquivo: ")
                        .append(fileDestino.getAbsolutePath())
                        .append("\n");
                LogUtil.error(FileUtil.class, ex, "Erro ao copiar arquivo:", fileDestino.getAbsolutePath());
            }
        }
    }

    /**
     * Método faz uma validação se o arquivo de origem é igual ao arquivo de
     * destino.
     *
     * @param source É o arquivo de origem.
     * @param destination É o arquivo de desntino.
     * @return Verdadeiro se igual. Falso caso contrário.
     */
    public static boolean equals(File source, File destination) {
        if (source == null || destination == null) {
            return false;
        }
        //compara diretório.
        if (source.isDirectory() || destination.isDirectory()) {
            if (!source.exists() || !destination.exists()) {
                return false;
            }
            if (source.getName().equals(destination.getName())) {
                return true;
            }
        }
        if (!source.exists() || !destination.exists()) {
            return false;
        }
        if (source.length() != destination.length()) {
            return false;
        }
        if (!source.getName().equals(destination.getName())) {
            return false;
        }
        if (source.lastModified() != destination.lastModified()) {
            return false;
        }
        return true;
    }

    /**
     * Método que exclui um arquivo.
     *
     * @param file Arquivo que será excluído.
     * @throws NegocioException
     */
    public static void deleteFile(File file) throws NegocioException {
        if (file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException ex) {
                throw new NegocioException("Erro ao deletar arquivo." + file.getAbsolutePath());
            }
        }
    }

    /**
     * Salva arquivo no servidor na pasta "uploads", de acordo com o contexto da
     * aplicação.
     *
     * @param context Contexto da aplicação.
     * @param inputStream Arquivo a ser salvo.
     * @param folder Pasta onde será salvo o arquivo.
     * @param fileName Nome do arquivo a ser salvo.
     * @throws NegocioException Caso arquivo já tenha sido enviado ou erro ao
     * tentar ler/escrever novo arquivo.
     */
    public static void saveFile(ServletContext context, InputStream inputStream, String folder, String fileName) throws NegocioException {
        if (fileName != null && !fileName.isEmpty()) {
            try {
                File f = new File(context.getRealPath("/" + folder + "/"));
                if (!f.exists()) {
                    f.mkdirs();
                }
                File file = new File(context.getRealPath("/" + folder) + "/" + fileName);
                if (file.exists()) {
                    throw new NegocioException("Arquivo " + file.getName() + " já enviado");
                }
                OutputStream out = new FileOutputStream(file);
                InputStream in = inputStream;
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                throw new NegocioException("Erro ao tentar salvar o arquivo");
            }
        } else {
            throw new NegocioException("Erro ao tentar salvar o arquivo");
        }
    }

    public static String fileToString(String filePath) throws NegocioException {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            StringBuilder stringBuilder = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException ex) {
            throw new NegocioException("Arquivo não encontrado.");
        } catch (IOException ex) {
            throw new NegocioException("Erro ao ler arquivo.");
        } catch (Exception ex) {
            throw new NegocioException("Erro inesperado ao ler arquivo.");
        }
    }
}
