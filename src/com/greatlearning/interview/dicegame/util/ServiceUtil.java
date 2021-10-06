package com.greatlearning.interview.dicegame.util;

import java.util.Random;

public class ServiceUtil {

    public static int generateRandomInteger(int range) {
        Random random = new Random();
        return random.nextInt(range) + 1;
    }
}
