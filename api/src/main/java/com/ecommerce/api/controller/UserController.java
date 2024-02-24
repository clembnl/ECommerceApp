package com.ecommerce.api.controller;

import com.ecommerce.api.dto.ResponseDto;
import com.ecommerce.api.dto.user.SignInDto;
import com.ecommerce.api.dto.user.SignInResponseDto;
import com.ecommerce.api.dto.user.SignUpDto;
import com.ecommerce.api.dto.user.SignUpResponseDto;
import com.ecommerce.api.exception.CustomException;
import com.ecommerce.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

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

    @PostMapping("/signin")
    public SignInResponseDto Signin(@RequestBody SignInDto signinDto) throws CustomException {
        return userService.signIn(signinDto);
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto getRole() {
        return new ResponseDto("success", "ROLE_ADMIN");
    }
}
