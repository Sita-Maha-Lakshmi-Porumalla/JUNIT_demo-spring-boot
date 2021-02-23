package com.rame.demospringboot.utils;

import java.util.function.Predicate;

public class PhoneNumberValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return (s != null && !s.equalsIgnoreCase("") && s.startsWith("+91") && s.length() == 13);

    }
}
