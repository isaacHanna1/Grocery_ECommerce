package com.watad.services;

public class TestMain {

	public static void main(String[] args) {
		
		String plainText = "localhost:8080/active/49512fef-968d-47c6-a437-89acba3db90b-expireIn-1708245385375?id=40";
		UrlManipulator manipulator = new UrlManipulator();
		String cipherText = "EiDQ8amjLTnxJGe1zOj4Hy1v5fYCIK8IZoktoPA/zPmj/DAuV/PbyGbHUNuGBcXjvA3lhvTjg7cLMBQoC7sqBy0Euhmt2DftZV3MQvTx65A=";
		System.out.println("the cipher text is :"+cipherText);
		String plainAgain = manipulator.decrypt(cipherText);
		System.out.println("the plain text is :"+plainAgain);

		String id = manipulator.extractId(plainAgain);
		String expireDate = manipulator.extractExpireDate(plainAgain);
		
		System.out.println("the expireDate is :"+expireDate);
		System.out.println("the id is :"+id);
		
		
	}

}
