package com.api.test_backend_java_bbl.service;

import com.api.test_backend_java_bbl.exception.NotFoundException;
import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.model.UpdateUserDetail;
import com.api.test_backend_java_bbl.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
        Long userId = Math.abs(new Random().nextLong());

        User newUser = User.builder()
                .email(payload.email())
                .id(userId)
                .name(payload.name())
                .username(payload.username())
                .phone(payload.phone())
                .website(payload.website())
                .build();
        userList.add(newUser);

        return newUser;
    }

    public User updateUser(Long userId, UpdateUserDetail payload) {
        User user = userList.stream().filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("User not found"));

        user.setUsername(payload.username());
        user.setName(payload.name());
        user.setEmail(payload.email());
        Optional.ofNullable(payload.website()).ifPresent(user::setWebsite);
        Optional.ofNullable(payload.phone()).ifPresent(user::setPhone);

        return user;
    }

    public void deleteUser(Long userId) {
        User user = userList.stream().filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("User not found"));

        userList.remove(user);
    }
}
