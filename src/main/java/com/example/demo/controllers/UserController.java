package com.example.demo.controllers;

import com.example.demo.mappers.UserMapper;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import com.example.demo.repositories.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final IUserRepository userRepository;

    @GetMapping("get-first-name")
    public String getFirstName() {
        return "John";
    }

    @GetMapping("get-user-list")
    public List<UserModel> getUserList() {
        return UserMapper.toModelList(userRepository.findAll());
    }
    @GetMapping("get-user-page-list")
    public UserPageModel getUserPageList(Integer pageNumber, Integer pageSize) {
        return UserMapper.toModelPagedList(userRepository.findAll(PageRequest.of(pageNumber, pageSize)));
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

        var entity = UserMapper.toEntity(userModel);
        userRepository.save(entity);

        return new ResponseEntity<UserModel>(userModel, HttpStatus.CREATED);
    }
}
