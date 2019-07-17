package com.example.speecherdemo;

import com.example.speecherdemo.UserModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackUser",
        threadPoolKey = "userPool",
        threadPoolProperties = {
            @HystrixProperty(name = "maxQueueSize", value = "10"),
            @HystrixProperty(name = "coreSize", value = "15")
        })
    public UserModel getUser(Long id) {
        return restTemplate.getForObject("http://user-client/user/" + id, UserModel.class);
    }

    private UserModel fallbackUser(Long id) {
        return new UserModel(id, "Noname");
    }
}