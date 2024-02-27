package com.example.logmasklib;

import com.example.logmasklib.feign.PersonFeignClient;
import com.example.logmasklib.feign.TodoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class LogMaskLibApplication implements CommandLineRunner {

    private final TodoFeignClient todoFeignClient;
    private final PersonFeignClient personFeignClient;

    public static void main(String[] args) {
        SpringApplication.run(LogMaskLibApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(todoFeignClient.getTodoInfo(1));
        System.out.println(personFeignClient.getPersonInfo());
    }
}
