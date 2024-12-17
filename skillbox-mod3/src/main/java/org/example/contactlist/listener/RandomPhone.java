package org.example.contactlist.listener;

import java.util.Random;

public class RandomPhone {
    public static String getRandomPhone(int codeCountry){
        StringBuilder builderPhone = new StringBuilder(String.valueOf(codeCountry));
        builderPhone.append(partPhoneNumber(3))
                .append(partPhoneNumber(3))
                .append(partPhoneNumber(2))
                .append(partPhoneNumber(2));
        return builderPhone.toString();
    }
    private static String partPhoneNumber(int lengthPart){
        int maxNumber = 0;
        for (int i = 0; i < lengthPart; i++){
            maxNumber = maxNumber + 9 * (int) Math.pow(10,i);
        }
        Random random = new Random();
        StringBuilder partPhoneNumber = new StringBuilder();
        String numberFirstPart = String.valueOf(random.nextInt(maxNumber-1)+1);
        partPhoneNumber.append("0".repeat(Math.max(0, (lengthPart - numberFirstPart.length())))).append(numberFirstPart);
        return partPhoneNumber.toString();
    }
}
