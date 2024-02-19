package com.ecommerce.api.controller;

import com.ecommerce.api.dto.ResponseDto;
import com.ecommerce.api.dto.user.SignInDto;
import com.ecommerce.api.dto.user.SignInResponseDto;
import com.ecommerce.api.dto.user.SignUpDto;
import com.ecommerce.api.dto.user.SignUpResponseDto;
import com.ecommerce.api.exception.AuthenticationFailException;
import com.ecommerce.api.exception.CustomException;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.AuthenticationService;
import com.ecommerce.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    /*
    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }
     */

    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignUpDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    //TODO token should be updated
    @PostMapping("/signin")
    public SignInResponseDto Signin(@RequestBody SignInDto signinDto) throws CustomException {
        return userService.signIn(signinDto);
    }
}
