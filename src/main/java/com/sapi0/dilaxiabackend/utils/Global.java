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
    public static final Pattern REGEX_EMAIL = Pattern.compile("\\w+([-+.']\\w+)@\\w+([-.]\\w+).\\w+([-.]\\w+)*");
    public static final Pattern REGEX_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!?\\-,./*_+=])(?=\\S+$).{8,}$");

}
