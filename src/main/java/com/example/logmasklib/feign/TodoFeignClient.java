package com.example.logmasklib.feign;

import com.example.logmasklib.dto.TodoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "todo-client",
        url = "${feign.todo.url}",
        path = "/todos"
)
public interface TodoFeignClient {

    @GetMapping("/{id}")
    TodoDto getTodoInfo(@PathVariable Integer id);

    @GetMapping
    List<TodoDto> getTodoInfoList();

}
