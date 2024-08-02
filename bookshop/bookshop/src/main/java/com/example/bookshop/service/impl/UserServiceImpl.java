//package com.example.bookshop.service.impl;
//
//
//import com.example.bookshop.dto.UserDTO;
//import com.example.bookshop.entity.User;
//import com.example.bookshop.exception.UserNotFoundException;
//import com.example.bookshop.mapper.UserMapper;
//import com.example.bookshop.payload.Request.SignInRequest;
//import com.example.bookshop.payload.Request.SignUpRequest;
//import com.example.bookshop.repository.UserRepository;
//import com.example.bookshop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public void changePassword(int id, String oldPassword, String newPassword) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
////            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
//            if (oldPassword.equals(user.get().getPassword())) {
////                user.get().setPassword(passwordEncoder.encode(newPassword));
//                user.get().setPassword(newPassword);
//                userRepository.save(user.get());
//            } else {
//                throw new UserNotFoundException("Invalid password");
//
//            }
//        }
//    }
//
//    //    @Autowired
////    private PasswordEncoder passwordEncoder;
//@Override
//public UserDTO registerUser(SignUpRequest userRegistrationDTO) {
//    if (userRepository.findByUsername(userRegistrationDTO.getUsername()) != null) {
//        throw new RuntimeException("Username already exists");
//    }
//
//    User user = new User();
//    user.setUsername(userRegistrationDTO.getUsername());
//    user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
//
////    user.setPassword(userRegistrationDTO.getPassword());
////    user.setPassword(passwordEncoder.encode());
//    user.setEmail(userRegistrationDTO.getEmail());
//    user.setFullname(userRegistrationDTO.getFullname());
//    user.setPhone(userRegistrationDTO.getPhone());
//    user.setRole(1); // Set default role to 1 (e.g., regular user)
//    user.setAvatar("defaultAvatar");
//    user.setClassification("NORMAL");
//
//    userRepository.save(user);
//
//    return new UserDTO(user.getId(), user.getUsername(), null, user.getEmail(), user.getRole(), user.getAvatar(), user.getFullname(), user.getPhone(), user.getClassification());
//}
//
//    @Override
//    public UserDTO loginUser(SignInRequest userLoginDTO) {
//        User user = userRepository.findByUsername(userLoginDTO.getUsername());
//        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid username or password");
//        }
////        if (user == null || !userLoginDTO.getPassword().equals(user.getPassword())) {
////            throw new RuntimeException("Invalid username or password");
////        }
//
//        return new UserDTO(user.getId(), user.getUsername(), null, user.getEmail(), user.getRole(), user.getAvatar(), user.getFullname(), user.getPhone(), user.getClassification());
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(userMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDTO getUserById(int id) {
//        return userRepository.findById(id)
//                .map(userMapper::toDTO)
//                .orElse(null);
//    }
//
//    @Override
//    public UserDTO createUser(UserDTO userDTO) {
//        User user = userMapper.toEntity(userDTO);
//        return userMapper.toDTO(userRepository.save(user));
//    }
//
//    @Override
//    public UserDTO updateUser(int id, UserDTO userDTO) {
//        if (userRepository.existsById(id)) {
//            User user = userMapper.toEntity(userDTO);
//            user.setId(id);
//            return userMapper.toDTO(userRepository.save(user));
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteUser(int id) {
//        userRepository.deleteById(id);
//    }
//}
//
//
//

package com.example.bookshop.service.impl;

import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.entity.User;
import com.example.bookshop.exception.UserNotFoundException;
import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.payload.Request.SignInRequest;
import com.example.bookshop.payload.Request.SignUpRequest;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(int id, String oldPassword, String newPassword) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                user.get().setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user.get());
            } else {
                throw new UserNotFoundException("Invalid password");
            }
        }
    }

    @Override
    public UserDTO registerUser(SignUpRequest userRegistrationDTO) {
        if (userRepository.findByUsername(userRegistrationDTO.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFullname(userRegistrationDTO.getFullname());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setRole(1); // Set default role to 1 (e.g., regular user)
        user.setAvatar("defaultAvatar.jpg");
        user.setClassification("NORMAL");

        userRepository.save(user);

        return new UserDTO(user.getId(), user.getUsername(), null, user.getEmail(), user.getRole(), user.getAvatar(), user.getFullname(), user.getPhone(), user.getClassification());
    }

    @Override
    public UserDTO loginUser(SignInRequest userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return new UserDTO(user.getId(), user.getUsername(), null, user.getEmail(), user.getRole(), user.getAvatar(), user.getFullname(), user.getPhone(), user.getClassification());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        if (userRepository.existsById(id)) {
            User user = userMapper.toEntity(userDTO);
            user.setId(id);
            return userMapper.toDTO(userRepository.save(user));
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
