package com.sprintgether.otserver.util;

import java.util.UUID;

import static com.sprintgether.otserver.common.AppConstant.DIGITS_LETTERS;
import static com.sprintgether.otserver.common.AppConstant.RANDOM;

public class TokenUtil {
    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }

    // Générer un code aléatoire contenant des chiffres et des lettres ayant une taille donnée
    public static String generateRandomDigitsLettersCode(int length) {
        String code = "";
        for (int i=0; i < length; i++)
        {
            int index = (int)(RANDOM.nextDouble()*DIGITS_LETTERS.length());
            code += DIGITS_LETTERS.substring(index, index+1);
        }
        return code;
    }

}
