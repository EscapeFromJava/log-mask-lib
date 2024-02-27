package com.example.logmasklib.mask.service.impl;

import com.example.logmasklib.mask.common.Type;
import com.example.logmasklib.mask.service.MaskHandler;
import com.example.logmasklib.mask.service.MaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaskHandlerImpl implements MaskHandler {

    private final MaskService maskService;

    @Override
    public String handle(Type type, String value) {
        return switch (type) {
            case EMAIL -> maskService.maskEmail(value);
            case LAST_NAME -> maskService.maskLastName(value);
            case PASSPORT -> maskService.maskPassport(value);
            default -> maskService.maskText(value);
        };
    }
}
