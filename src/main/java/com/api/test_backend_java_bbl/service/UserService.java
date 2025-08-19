package com.api.test_backend_java_bbl.service;

import com.api.test_backend_java_bbl.exception.DuplicateUserException;
import com.api.test_backend_java_bbl.exception.NotFoundException;
import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
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
        userList = new ArrayList<>(Arrays.asList(userResponse));
    }

    public List<User> getListUser(){
        return userList;
    }

    public User findByUserId(Long userId) {
        return userList.stream().filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createNewUser(CreateNewUserRequest payload) {
        boolean userExists = userList.stream().anyMatch(user -> user.getId().equals(payload.id()));

        if (userExists){
            throw new DuplicateUserException("User already exists");
        }
        User newUser = User.builder()
                .email(payload.email())
                .id(payload.id())
                .name(payload.name())
                .username(payload.username())
                .phone(payload.phone())
                .website(payload.website())
                .build();
        userList.add(newUser);

        return newUser;
    }
}
