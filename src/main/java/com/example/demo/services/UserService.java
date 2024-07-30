package com.example.demo.services;

import com.example.demo.exceptions.user.UserAlreadyExistException;
import com.example.demo.exceptions.user.UserException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.mappers.UserProductsMapper;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import com.example.demo.models.UserProductsModel;
import com.example.demo.repositories.IUserProductsRepository;
import com.example.demo.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserProductsRepository userProductsRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> findAll() {
        return UserMapper.toModelList(userRepository.findAll());
    }

    @Override
    public UserPageModel findPageList(PageRequest pageRequest) {
        return UserMapper.toModelPagedList(userRepository.findAll(pageRequest));
    }

    @Override
    public UserModel create(UserModel model) {
        var user = UserMapper.toEntity(model, passwordEncoder);
//        var existingUser = userRepository.findByEmail(model.getEmail());
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User with email " + model.getEmail() + " already exists");
        }
        return UserMapper.toModel(userRepository.save(user));
    }

    @Override
    public UserModel update(UserModel model) {
        var entity = UserMapper.toEntity(model, passwordEncoder);
        try {
            return UserMapper.toModel(userRepository.save(entity));
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId) {
        userRepository.delete(userRepository
                .findById(userId).orElseThrow(
                        ()-> new UserException("User not found")
                ));
    }

    @Override
    public List<UserProductsModel> findUserProducts() {
        return UserProductsMapper.toModelList(userProductsRepository.findAll());
    }
}
