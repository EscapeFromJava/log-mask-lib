package com.example.logmasklib.mask.service.impl;

import com.example.logmasklib.mask.service.MaskService;
import org.springframework.stereotype.Component;

@Component
public class MaskServiceImpl implements MaskService {

    private final static String SYM_STAR = "*";
    private final static String SYM_AT = "@";

    @Override
    public String maskEmail(String email) {
        String[] arr = email.split(SYM_AT);
        String name = arr[0];
        String domain = arr[1];
        String newName = SYM_STAR.repeat(3) + name.substring(name.length() - 3);
        String newDomain = SYM_STAR.repeat(2) + domain.substring(2);
        return newName + SYM_AT + newDomain;
    }

    @Override
    public String maskLastName(String lastName) {
        return lastName.charAt(0) + SYM_STAR.repeat(6) + lastName.charAt(lastName.length() - 1);
    }

    @Override
    public String maskPassport(String passport) {
        return passport.substring(0, 2) + SYM_STAR.repeat(6) + passport.substring(passport.length() - 2);
    }

    @Override
    public String maskText(String text) {
        return SYM_STAR.repeat(text.length());
    }

}
