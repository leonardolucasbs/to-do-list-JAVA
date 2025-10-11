package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.PublicUserDTO;
import com.leonardolucs.todolist.models.dto.UserDTO;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.User;
import com.leonardolucs.todolist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    public User findEntityById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private PublicUserDTO createPublicUserDTO(User user) {

        return new PublicUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTasks().stream().map(Task::toDto).collect(Collectors.toList()));

    }

    public ResponseEntity<?> getUser(Long id) {
        Optional<User> foundUser = findUserById(id);
        return foundUser.isEmpty()
                ? ResponseEntity.badRequest().body("User not registered.")
                : ResponseEntity.ok(createPublicUserDTO(foundUser.get()));
    }

    public List<PublicUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<PublicUserDTO> publicUsers = new ArrayList<>();

        for (User user : users) {
            PublicUserDTO publicUserDTO = createPublicUserDTO(user);
            publicUsers.add(publicUserDTO);
        }

        return publicUsers;
    }

    public void createUser(UserDTO userDTO) {
        User newUser = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<?> updateUser(Long id, UserDTO userDTO) {
        Optional<User> foundUser = findUserById(id);

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        User user = foundUser.get();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok(createPublicUserDTO(user));
    }
}
