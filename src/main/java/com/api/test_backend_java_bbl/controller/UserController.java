package com.api.test_backend_java_bbl.controller;

import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.model.UpdateUserDetail;
import com.api.test_backend_java_bbl.model.User;
import com.api.test_backend_java_bbl.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getListUsers(){
        return ResponseEntity.ok(userService.getListUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetail(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@Valid @RequestBody CreateNewUserRequest payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(payload));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetail(@PathVariable Long userId, @Valid @RequestBody UpdateUserDetail payload){
        return ResponseEntity.ok(userService.updateUser(userId,payload));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok(String.format("Delete user %d success",userId));
    }
}
