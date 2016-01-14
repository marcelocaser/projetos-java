package br.com.core.util;

import br.com.core.exception.NegocioException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author marcelocaser
 */
public class CriptografiaUtil {

    private static final String key = "CEPGOCOMBRCONSULTADECEPGRATIS";
    private static byte[] encryptKey;
    private static KeySpec keySpec;
    private static MessageDigest md5;
    private static Cipher cipher;
    private static SecretKeyFactory secretKeyFactory;
    private static SecretKey secretKey;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
            encryptKey = key.getBytes("UTF-8");
            cipher = Cipher.getInstance("DESede");
            keySpec = new DESedeKeySpec(encryptKey);
            secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            secretKey = secretKeyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
    }

    private CriptografiaUtil() {
    }

    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }

    public static String gerarMD5(String pwd) {
        if (md5 != null) {
            return new String(hexCodes(md5.digest(pwd.getBytes())));
        }
        return null;
    }

    /**
     * Método que criptografa um valor.
     *
     * @param value É o valor que será criptografado.
     * @return Valor criptografado.
     *
     * @throws NegocioException
     */
    public static String encryptBase64(String value) throws NegocioException {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(value.getBytes("UTF-8"));
            //BASE64Encoder encoder = new BASE64Encoder();
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipherText);
            //return encoder.encode(cipherText);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            throw new NegocioException("Chave inválida! " + ex.getMessage());
        }
    }

    /**
     * Método que decifra um valor.
     *
     * @param value É o valor que será decifrado.
     * @return Valor decifrado.
     *
     * @throws NegocioException
     */
    public static String decryptBase64(String value) throws NegocioException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //BASE64Decoder dec = new BASE64Decoder();
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decipherText = cipher.doFinal(decoder.decode(value));
            //byte[] decipherText = cipher.doFinal(dec.decodeBuffer(value));
            return new String(decipherText);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new NegocioException("Chave inválida! " + ex.getMessage());
        }
    }
}
