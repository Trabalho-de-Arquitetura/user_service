package com.user_service.controller;

import com.user_service.dto.CreateUserInput;
import com.user_service.dto.UpdateUserInput;
import com.user_service.entity.User;
import com.user_service.entity.UserRole;
import com.user_service.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public User findUserById(@Argument UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public User findUserByEmail(@Argument String email) {
        return userRepository.findByEmail(email);
    }

    @QueryMapping
    public List<User> findUsersByRole(@Argument UserRole role) {
        return userRepository.findAllByRole(role);
    }

    @QueryMapping
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @MutationMapping
    public User saveUser(@Argument CreateUserInput input) {
        User user = new User();
        user.setName(input.name);
        user.setEmail(input.email);
        user.setAffiliatedSchool(input.affiliatedSchool);

        String encryptedPassword = new BCryptPasswordEncoder().encode(input.password);

        user.setPassword(encryptedPassword);
        user.setRole(input.role);
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        User user = userRepository.findById(input.id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (input.name != null) {
            user.setName(input.name);
        }
        if (input.email != null) {
            user.setEmail(input.email);
        }
        if (input.affiliatedSchool != null) {
            user.setAffiliatedSchool(input.affiliatedSchool);
        }
        if (input.password != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(input.password);
            user.setPassword(encryptedPassword);
        }
        if (input.role != null) {
            user.setRole(input.role);
        }

        return userRepository.save(user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    // SchemaMapping para resolver o tipo User para a federação
    // O API Gateway usará isso quando outro serviço referenciar um User
    // e precisar que o User Service resolva esse User a partir do ID.
    @SchemaMapping(typeName = "UserDTO", field = "id")
    public Optional<UUID> __resolveReference(User user) {
        return userRepository.findById(user.getId())
                .map(User::getId);
    }
}