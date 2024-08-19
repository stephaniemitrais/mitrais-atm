package com.mitrais.atm.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueRandomNumberGenerator {
    private static final Random random = new Random();
    private static final Set<Integer> usedNumbers = new HashSet<>();

    public static String generateUniqueRandomNumber() {
        int number;
        do {
            number = random.nextInt(900000) + 100000;
        } while (usedNumbers.contains(number));
        usedNumbers.add(number);
        return String.valueOf(number);
    }
}