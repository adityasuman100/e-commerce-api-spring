package com.ecommerce.api.service;

import com.ecommerce.api.dto.request.LoginUserReqDto;
import com.ecommerce.api.dto.request.RegisterUserReqDto;
import com.ecommerce.api.dto.request.UpdateShippingAddressReqDto;
import com.ecommerce.api.dto.response.LoginUserResDto;
import com.ecommerce.api.dto.response.RegisterUserResDto;
import com.ecommerce.api.dto.response.UpdateShippingAddressResDto;
import com.ecommerce.api.model.ShippingAddress;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.security.JwtService;
import java.util.List;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

// import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@CustomLog
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    // private final PasswordEncoder passwordEncoder;
    // private final JwtService jwtService;

    // /**
    //  * Register User
    //  * @param registerUserReqDto
    //  * @return
    //  */

    // public RegisterUserResDto
    // registerUser(RegisterUserReqDto registerUserReqDto) throws Exception {

    //     List<User> existingUsers =
    //         userRepository.findByEmail(registerUserReqDto.getEmail());
    //     if (existingUsers.size() > 0) {
    //         throw new Exception("User already exists");
    //     }

    //     User user = modelMapper.map(registerUserReqDto, User.class);
    //     user.setPassword(
    //         passwordEncoder.encode(registerUserReqDto.getPassword()));
    //     log.info("creating user {}", user);

    //     User savedUser = userRepository.insert(user);
    //     log.info("saved user {}", savedUser);

    //     RegisterUserResDto registerUserResDto =
    //         modelMapper.map(savedUser, RegisterUserResDto.class);
    //     return registerUserResDto;
    // }

    // public LoginUserResDto
    // loginUser(@RequestBody LoginUserReqDto loginUserReqDto) throws Exception
    // {
    //     List<User> existingUsers =
    //         userRepository.findByEmail(loginUserReqDto.getEmail());
    //     if (existingUsers.isEmpty()) {
    //         throw new Exception("User does not exist");
    //     }

    //     if (passwordEncoder.matches(loginUserReqDto.getPassword(),
    //                                 existingUsers.get(0).getPassword())) {
    //         return LoginUserResDto.builder()
    //             .user(existingUsers.get(0))
    //             .token(jwtService.generateToken(existingUsers.get(0)))
    //             .build();
    //     }
    //     throw new Exception("Username or Password incorrect");
    // }

    public User getUserProfile() {
        UserDetails userDetails =
            (UserDetails)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserDetails user = loadUserByUsername(userDetails.getUsername());
        log.info("getUserProfile::{}", user);
        return (User)user;
    }

    public UpdateShippingAddressResDto updateShippingAddress(
        UpdateShippingAddressReqDto updateShippingAddressReqDto) {
        UserDetails userDetails =
            (UserDetails)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        ShippingAddress shippingAddress =
            modelMapper.map(updateShippingAddressReqDto, ShippingAddress.class);
        User existingUser = this.findUserByEmail(userDetails.getUsername());
        existingUser.setShippingAddress(shippingAddress);
        existingUser.setHasShippingAddress(true);
        userRepository.save(existingUser);
        log.info("shipping address updated");
        return modelMapper.map(shippingAddress,
                               UpdateShippingAddressResDto.class);
    }

    private User findUserByEmail(String email) {
        List<User> existingUsers = userRepository.findByEmail(email);
        if (existingUsers.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return existingUsers.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        List<User> existingUsers = userRepository.findByEmail(username);
        if (existingUsers.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return existingUsers.get(0);
    }
}
