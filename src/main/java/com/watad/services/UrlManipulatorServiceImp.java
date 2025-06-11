package com.watad.services;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlManipulatorServiceImp implements UrlManipulatorService {

    private final String secretKey;

    public UrlManipulatorServiceImp() {
        this.secretKey = "M1sMNN8kmpGyI5JyoTnaRwVN1I9kuKQ4tVWN0KFctZs=";
    }

    public String encrypt(String input) {
        try {
            Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String encryptedInput) {
        try {
            // Add debugging logs
            System.out.println("=== DECRYPT DEBUG ===");
            System.out.println("Input received: [" + encryptedInput + "]");
            System.out.println("Input length: " + encryptedInput.length());
            System.out.println("Input ends with: " + encryptedInput.substring(Math.max(0, encryptedInput.length() - 5)));

            // Check for any non-Base64 characters
            if (!encryptedInput.matches("[A-Za-z0-9_-]*")) {
                System.out.println("WARNING: Input contains non-URL-safe-Base64 characters!");
            }

            // Decode the encrypted token from Base64
            byte[] encryptedBytes = Base64.getUrlDecoder().decode(encryptedInput);
            System.out.println("Successfully decoded Base64, byte array length: " + encryptedBytes.length);

            Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);

            System.out.println("Decryption successful!");
            return result;
        } catch (Exception e) {
            System.out.println("Decryption failed with error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


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
