package com.user_service.controller;

import com.user_service.dto.CreateUserInput;
import com.user_service.dto.UpdateUserInput;
import com.user_service.dto.LoginReturnDTO;
import com.user_service.entity.User;
import com.user_service.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public User userById(@Argument UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public User userByEmail(@Argument String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public List<User> userByRole(@Argument String role) {
        return userRepository.findByRole(role);
    }

    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        User user = new User();
        user.setName(input.name);
        user.setEmail(input.email);
        user.setAffiliatedSchool(input.affiliatedSchool);
        user.setRole(input.role);
        user.setPassword(input.password);
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument UUID id, @Argument UpdateUserInput input) {
        return userRepository.findById(id).map(user -> {
            if (input.name != null) user.setName(input.name);
            if (input.email != null) user.setEmail(input.email);
            if (input.affiliatedSchool != null) user.setAffiliatedSchool(input.affiliatedSchool);
            if (input.role != null) user.setRole(input.role);
            if (input.password != null) user.setPassword(input.password);
            return userRepository.save(user);
        }).orElse(null);
    }

    @MutationMapping
    public User deleteUser(@Argument UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
        return userOptional.orElse(null);
    }

    @MutationMapping
    public LoginReturnDTO login(@Argument String email, @Argument String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return new LoginReturnDTO(user, "fake-jwt-token");
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    @MutationMapping
    public Boolean changePassword(@Argument String email, @Argument String password, @Argument String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                user.setPassword(newPassword);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @SchemaMapping(typeName = "User", field = "id")
    public Optional<User> __resolveReference(User user) {
        return userRepository.findById(user.getId());
    }
}
