package com.unifavipTechTeam.favip.service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoService {

    private static final String ALGORITHM = "AES";
    private static final String KEY_FILE = "secret.key";
    private static SecretKey secretKey;

    static {
        try {
            File keyFile = new File(KEY_FILE);
            if (keyFile.exists()) {
                try (FileInputStream fis = new FileInputStream(keyFile)) {
                    byte[] keyBytes = new byte[16];
                    fis.read(keyBytes);
                    secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
                }
            } else {
                secretKey = generateKey();
                try (FileOutputStream fos = new FileOutputStream(keyFile)) {
                    fos.write(secretKey.getEncoded());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize secret key", e);
        }
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
