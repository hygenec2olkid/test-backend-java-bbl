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
public class userController {
    private final UserService userService;

    public userController(UserService userService) {
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
    public ResponseEntity<?> updateUserDetail(@Valid @PathVariable Long userId,@RequestBody UpdateUserDetail payload){
        return ResponseEntity.ok(userService.updateUser(userId,payload));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        return ResponseEntity.ok("delete user");
    }
}
