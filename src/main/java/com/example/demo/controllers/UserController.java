package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping("get-first-name")
    public String getFirstName() {
        return "John";
    }

    @GetMapping("get-first-name-list")
    public List<String> getFirstNameList() {
        return List.of("John", "Jane");
    }

    @PostMapping("create-user")
    public boolean createUser(String firstName, String lastName) {
        return true;
    }
    @PostMapping("create-user-body")
    public ResponseEntity<?> createUserBody(@RequestBody @Valid UserModel userModel, BindingResult result) {
        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body(result);
            return new ResponseEntity<>("Registration failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UserModel>(userModel, HttpStatus.CREATED);
    }
}
