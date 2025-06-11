package com.watad.services;

public interface UrlManipulatorService {

    public  String extractId(String url);
    public  String extractExpireDate(String url);
    public String decrypt(String encryptedInput);
    public String encrypt(String input);

}
