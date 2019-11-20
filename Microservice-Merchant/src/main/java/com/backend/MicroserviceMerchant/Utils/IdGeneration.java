package com.backend.MicroserviceMerchant.Utils;

import java.util.Random;

public class IdGeneration {
    public static String generateMerchantId (String FirstName, String LastName) {
        String generatedMerchantId = "";
        Random random = new Random();
        generatedMerchantId = FirstName + '.' +  LastName + '_' + random.nextInt(99);
        return generatedMerchantId;
    }
}
