package com.watad.services;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.SecureRandom;

@Service
public class UrlManipulator {

    private final String secretKey;

    public UrlManipulator() {
        this.secretKey = "M1sMNN8kmpGyI5JyoTnaRwVN1I9kuKQ4tVWN0KFctZs=";
    }

    public String encrypt(String input) {
        try {
            Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String encryptedInput) {
        try {
            // Decode the encrypted token from Base64
            byte[] encryptedBytes = Base64.getUrlDecoder().decode(encryptedInput);
            Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    private static String generateSecretKey() {
//        byte[] randomBytes = new byte[32]; // 32 bytes = 256 bits
//        SecureRandom secureRandom = new SecureRandom();
//        secureRandom.nextBytes(randomBytes);
//        return Base64.getEncoder().encodeToString(randomBytes);
//    }
    

    public  String extractExpireDate(String url) {
        Pattern pattern = Pattern.compile("expireIn-(\\d+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public  String extractId(String url) {
        Pattern pattern = Pattern.compile("id=(\\d+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
