package ksmart.ks50team01.user.member.login.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private static final String CHAR_POOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();

        List<String> charCategories = new ArrayList<>();
        charCategories.add(CHAR_POOL.substring(0, 10)); // 숫자
        charCategories.add(CHAR_POOL.substring(10, 36)); // 대문자
        charCategories.add(CHAR_POOL.substring(36)); // 소문자

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int randomIndex = random.nextInt(charCategory.length());
            char randomChar = charCategory.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        String randomPassword = generateRandomPassword();
        System.out.println("Random Password: " + randomPassword);
    }
}