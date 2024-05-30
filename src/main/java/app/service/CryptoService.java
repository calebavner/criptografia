package app.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {

    private static final StrongTextEncryptor ENCRYPTOR;

    static {
        ENCRYPTOR = new StrongTextEncryptor();
        ENCRYPTOR.setPassword(System.getenv("APP_KEY"));
    }

    public static String encrypt(String text) {
        return ENCRYPTOR.encrypt(text);
    }

    public static String decrypt(String encryptedText) {
        return ENCRYPTOR.decrypt(encryptedText);
    }
}
