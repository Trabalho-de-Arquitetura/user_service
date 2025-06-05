package com.user_service.controller;

import com.user_service.dto.CreateUserInput;
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

    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        User user = new User();
        user.setName(input.name);
        user.setEmail(input.email);
        user.setAffiliatedSchool(input.affiliatedSchool);
        user.setRole(input.role);
        return userRepository.save(user);
    }

    // SchemaMapping para resolver o tipo User para a federação
    // O API Gateway usará isso quando outro serviço referenciar um User
    // e precisar que o User Service resolva esse User a partir do ID.
    @SchemaMapping(typeName = "User", field = "id")
    public Optional<UUID> __resolveReference(User user) {
        return Optional.ofNullable(userRepository.findById(user.getId()).get().getId());
    }
}