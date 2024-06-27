package com.ecommerce.api.controller;

import com.ecommerce.api.dto.request.LoginUserReqDto;
import com.ecommerce.api.dto.request.RegisterUserReqDto;
import com.ecommerce.api.dto.request.UpdateShippingAddressReqDto;
import com.ecommerce.api.dto.response.LoginUserResDto;
import com.ecommerce.api.dto.response.RegisterUserResDto;
import com.ecommerce.api.dto.response.UpdateShippingAddressResDto;
import com.ecommerce.api.model.User;
import com.ecommerce.api.service.AuthService;
import com.ecommerce.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RegisterUserResDto
    registerUser(@RequestBody RegisterUserReqDto registerUserReqDto)
        throws Exception {
        return authService.registerUser(registerUserReqDto);
    }

    @PostMapping("/login")
    public LoginUserResDto
    loginUser(@RequestBody LoginUserReqDto loginUserReqDto) throws Exception {
        return authService.loginUser(loginUserReqDto);
    }

    @GetMapping("/profile")
    @ResponseStatus(code = HttpStatus.OK)
    public User getUserProfile() {
        return userService.getUserProfile();
    }

    @PutMapping("/update/shipping")
    public UpdateShippingAddressResDto updateShippingAddress(
        @RequestBody UpdateShippingAddressReqDto updateShippingAddressReqDto) {
        return null;
    }
}
