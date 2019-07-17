package com.example.messageclient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private Map<Long, MessageModel> db;

    public MessageController() {
        db = Stream.of(new Object[][] {
            {1L, new MessageModel(1L, 1L, "Some words about microservices")},
            {2L, new MessageModel(2L, 1L, "Some words about Java")},
            {3L, new MessageModel(3L, 1L, "Comparing Java and Python")},
            {4L, new MessageModel(4L, 2L, "I think about Spring Cloud Config Server")},
            {5L, new MessageModel(5L, 2L, "Nevermind! Gotcha!")},
            {6L, new MessageModel(6L, 3L, "Let's break!")}
        }).collect(Collectors.toMap(data -> (Long) data[0], data -> (MessageModel) data[1]));
    }

    @GetMapping("/{id}")
    public MessageModel getMessage(@PathVariable Long id) {
        return db.get(id);
    }

    @GetMapping("/author/{id}")
    public List<MessageModel> getMessages(@PathVariable Long id) {
        List<MessageModel> result = db.values().stream().filter(p -> p.getAuthor().equals(id)).collect(Collectors.toList());

        return result;
    }
}