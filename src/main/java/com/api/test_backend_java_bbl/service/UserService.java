package com.api.test_backend_java_bbl.service;

import com.api.test_backend_java_bbl.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private List<User> userList;
    private final RestTemplate restTemplate;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    @PostConstruct
    public void loadUsers(){
        String url = "https://jsonplaceholder.typicode.com/users";
        User[] userResponse = restTemplate.getForObject(url,User[].class);
        assert userResponse != null;
        userList = Arrays.stream(userResponse).toList();
    }

    public List<User> getListUser(){
        return userList;
    }
}
