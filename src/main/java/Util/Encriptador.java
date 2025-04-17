package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {

    /**
     * Encripta una cadena de texto en MD5
     * Usado para la contraseña de los usuarios
     * @param input cadena de texto a encriptar
     * @return cadena de texto encriptada
     */

    public static String encryptMD5(String input) {
        try {
            // instancia de MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // convierte la entrada en bytes y calcula el hash
            byte[] digest = md.digest(input.getBytes());
            // convierte el hash en una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}