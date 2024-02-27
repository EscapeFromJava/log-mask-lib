package com.example.logmasklib.mask.service;

public interface MaskService {

    String maskEmail(String email);

    String maskLastName(String lastName);

    String maskPassport(String passport);

    String maskText(String text);
}
