package com.example.logmasklib.feign;

import com.example.logmasklib.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "person-client",
        url = "${feign.person.url}"
)
public interface PersonFeignClient {

    @GetMapping
    PersonDto getPersonInfo();

}
