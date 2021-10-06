package com.greatlearning.interview.dicegame.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceUtilTest {

    @Test
    public void validateTheIntegerGeneratedByGenerateRandomInteger() throws Exception{
        int result = ServiceUtil.generateRandomInteger(5);
        Assertions.assertTrue(result <= 5);
        Assertions.assertTrue(result >= 1);
    }
}
