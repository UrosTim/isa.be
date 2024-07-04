package com.example.demo.controllers;

import com.example.demo.models.UserModel;
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
    public UserModel createUserBody(@RequestBody UserModel userModel) {
        return userModel;
    }
}
