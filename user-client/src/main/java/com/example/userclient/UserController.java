package com.example.userclient;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private Map<Long, UserModel> db;

    public UserController() {
        db = Stream.of(new Object[][] {
            {1L, new UserModel(1L, "Peter")},
            {2L, new UserModel(2L, "Ivan")},
            {3L, new UserModel(3L, "Fedor")}
        }).collect(Collectors.toMap(data -> (Long) data[0], data -> (UserModel) data[1]));
    }

    @GetMapping("/{id}")
    public UserModel getItem(@PathVariable Long id) {
        return db.get(id);
    }
}