package com.example.speecherdemo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

import com.example.speecherdemo.MessageService;
import com.example.speecherdemo.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<FlowModel> getData() {
        List<FlowModel> data = new LinkedList<>();

        LongStream.range(1, 4).forEach(nbr -> {
            UserModel user = userService.getUser(nbr);
            List<MessageModel> messages = messageService.getMessageList(nbr);
            
            data.add(new FlowModel(user, messages));
        });

        return data;
    }
}