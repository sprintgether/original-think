package com.sprintgether.otserver.common;

import java.security.SecureRandom;
import java.util.Random;

public class AppConstant {
    public static final Random RANDOM = new SecureRandom();
    public static final String DIGITS_LETTERS = "abcdefghjkmnpqrstuvwxyz23456789";
    public static final String CLIENT_ORIGIN_HEADER = "origin";
}

