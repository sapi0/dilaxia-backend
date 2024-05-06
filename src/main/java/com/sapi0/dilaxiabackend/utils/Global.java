package com.sapi0.dilaxiabackend.utils;

import java.util.regex.Pattern;

public class Global {

    public static final int BCRYPT_COST = 13;

    public static final String STUDENT_EMAIL_SUFFIX = "@aldini.istruzioneer.it";
    public static final String PROFESSOR_EMAIL_SUFFIX = "@avbo.it";

    public static final int USER_TYPE_PROFESSOR = 2;
    public static final int USER_TYPE_STUDENT = 1;
    public static final int USER_TYPE_EXTERNAL = 0;

    public static final Pattern REGEX_LETTERS_SPACES = Pattern.compile("^[ A-Za-z]+$");
    public static final Pattern REGEX_EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    public static final Pattern REGEX_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!?\\-,./*_+=])(?=\\S+$).{8,}$");

}
