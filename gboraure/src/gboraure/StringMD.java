package gboraure;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringMD
        implements Serializable {

    private static final long serialVersionUID = 1;
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        byte[] arrby = digest;
        int n = arrby.length;
        int n2 = 0;
        while (n2 < n) {
            byte aux = arrby[n2];
            int b = aux & 255;
            if (Integer.toHexString(b).length() == 1) {
                hash = String.valueOf(hash) + "0";
            }
            hash = String.valueOf(hash) + Integer.toHexString(b);
            ++n2;
        }
        return hash;
    }

    public String getStringMessageDigest(String message, String algorithm) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException messageDigest) {
            // empty catch block
        }
        return StringMD.toHexadecimal(digest);
    }

    public int randomKey() {
        int numAleatorio = (int) (Math.random() * 9.87654392E8 + 1.0);
        return numAleatorio;
    }
}
