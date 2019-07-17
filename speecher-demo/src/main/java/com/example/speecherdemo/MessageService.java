package com.example.speecherdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.speecherdemo.MessageModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackMessage",
        threadPoolKey = "userPool",
        threadPoolProperties = {
            @HystrixProperty(name = "maxQueueSize", value = "10"),
            @HystrixProperty(name = "coreSize", value = "15")
        })
    public List<MessageModel> getMessageList(Long id) {
        MessageModel[] response = restTemplate.getForObject("http://message-client/message/author/" + id, MessageModel[].class);
        return Arrays.stream(response).collect(Collectors.toList());
    }

    private List<MessageModel> fallbackMessage(Long id) {
        return new ArrayList<>();
    }
}