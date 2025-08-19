package com.api.test_backend_java_bbl.controller;

import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.model.UpdateUserDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class userController {
    @GetMapping
    public ResponseEntity<?> getListUsers(){
        return ResponseEntity.ok("get list users");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable String userId){
        return ResponseEntity.ok("get user detail");
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
