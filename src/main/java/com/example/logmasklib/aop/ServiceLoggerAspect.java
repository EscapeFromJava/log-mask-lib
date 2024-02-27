package com.example.logmasklib.aop;

import com.example.logmasklib.mask.common.Mask;
import com.example.logmasklib.mask.service.MaskHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ServiceLoggerAspect {

    private final ObjectMapper objectMapper;
    private final MaskHandler maskHandler;

    @Pointcut("execution(* com.example.logmasklib.feign..*(..))")
    public void callFeignClient() {
    }

    @Before("callFeignClient()")
    public void before(JoinPoint jp) {
        String fullMethodName = getFullMethodName(jp);
        String args = getArgs(jp);
        if (Strings.isNotBlank(args)) {
            log.debug("Вызов метода {} с параметрами {}", fullMethodName, args);
        } else {
            log.debug("Вызов метода {}", fullMethodName);
        }
    }

    @SneakyThrows
    @AfterReturning(pointcut = "callFeignClient()", returning = "result")
    public void after(JoinPoint jp, Object result) {
        String logBody = objectMapper.writeValueAsString(result);

        Field[] declaredFields = result.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Mask.class)) {
                field.setAccessible(true);
                String value = (String) field.get(result);
                String maskedValue = maskHandler.handle(field.getAnnotation(Mask.class).value(), value);
                logBody = logBody.replace(value, maskedValue);
            }
        }

        String fullMethodName = getFullMethodName(jp);
        String args = getArgs(jp);
        if (Strings.isNotBlank(args)) {
            log.debug("Тело ответа для метода {} с параметрами {}: {}", fullMethodName, args, logBody);
        } else {
            log.debug("Тело ответа для метода {}: {}", fullMethodName, logBody);
        }
    }

    private String getFullMethodName(JoinPoint jp) {
        return jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName() + "()";
    }

    private String getArgs(JoinPoint jp) {
        return Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

}
