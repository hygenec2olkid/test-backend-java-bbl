package com.api.test_backend_java_bbl.controller;

import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.model.UpdateUserDetail;
import com.api.test_backend_java_bbl.model.User;
import com.api.test_backend_java_bbl.service.UserService;
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
    public ResponseEntity<?> getUserDetail(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody CreateNewUserRequest payload){
        return ResponseEntity.ok("create new user");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetail(@PathVariable String userId,@RequestBody UpdateUserDetail payload){
        return ResponseEntity.ok("update user detail");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        return ResponseEntity.ok("delete user");
    }
}
