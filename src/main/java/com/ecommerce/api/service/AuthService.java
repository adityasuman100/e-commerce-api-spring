package com.ecommerce.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.api.dto.request.LoginUserReqDto;
import com.ecommerce.api.dto.request.RegisterUserReqDto;
import com.ecommerce.api.dto.response.LoginUserResDto;
import com.ecommerce.api.dto.response.RegisterUserResDto;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.security.JwtService;

import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CustomLog
public class AuthService {
    
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Register User
     * @param registerUserReqDto
     * @return
     */

    public RegisterUserResDto
    registerUser(RegisterUserReqDto registerUserReqDto) throws Exception {

        List<User> existingUsers =
            userRepository.findByEmail(registerUserReqDto.getEmail());
        if (existingUsers.size() > 0) {
            throw new Exception("User already exists");
        }

        User user = modelMapper.map(registerUserReqDto, User.class);
        user.setPassword(
            passwordEncoder.encode(registerUserReqDto.getPassword()));
        log.info("creating user {}", user);

        User savedUser = userRepository.insert(user);
        log.info("saved user {}", savedUser);

        RegisterUserResDto registerUserResDto =
            modelMapper.map(savedUser, RegisterUserResDto.class);
        return registerUserResDto;
    }

    public LoginUserResDto
    loginUser(@RequestBody LoginUserReqDto loginUserReqDto) throws Exception {
        List<User> existingUsers =
            userRepository.findByEmail(loginUserReqDto.getEmail());
        if (existingUsers.isEmpty()) {
            throw new Exception("User does not exist");
        }

        if (passwordEncoder.matches(loginUserReqDto.getPassword(),
                                    existingUsers.get(0).getPassword())) {
            return LoginUserResDto.builder()
                .user(existingUsers.get(0))
                .token(jwtService.generateToken(existingUsers.get(0)))
                .build();
        }
        throw new Exception("Username or Password incorrect");
    }
}
