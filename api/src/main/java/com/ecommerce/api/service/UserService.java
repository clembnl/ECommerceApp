package com.ecommerce.api.service;

import com.ecommerce.api.config.MessageStrings;
import com.ecommerce.api.config.ResponseStatus;
import com.ecommerce.api.dto.ResponseDto;
import com.ecommerce.api.dto.user.*;
import com.ecommerce.api.exception.AuthenticationFailException;
import com.ecommerce.api.exception.CustomException;
import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.utils.Helper;
import com.ecommerce.api.jwt.JwtUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto signUp(SignUpDto signupDto)  throws CustomException {
        // Check to see if the current email address has already been registered.
        if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // Check to see if the current username has already been registered.
        if (Helper.notNull(userRepository.findByUsername(signupDto.getUsername()))) {
            // If the username has been registered then throw an exception.
            throw new CustomException("User already exists");
        }

        User user = new User(signupDto.getUsername(), signupDto.getEmail(),
                Role.user, encoder.encode(signupDto.getPassword()));

        try {
            // save the User
            userRepository.save(user);
            // success in creating
            return new SignUpResponseDto(ResponseStatus.success.toString(), MessageStrings.USER_CREATED);
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }

    public SignInResponseDto signIn(SignInDto signinDto) throws CustomException {

        /*
        // first find User by username
        User user = userRepository.findByUsername(signinDto.getUsername());
        if(!Helper.notNull(user)){
            throw  new AuthenticationFailException("user not present");
        }
        // check if password is right
        if (!user.getPassword().equals(signinDto.getPassword())){
            // password does not match
            throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
        }
        */

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // first find User by username
        User user = userRepository.findByUsername(signinDto.getUsername());
        if(!Helper.notNull(user)){
            throw  new AuthenticationFailException("user not present");
        }

        return new SignInResponseDto ("success", jwt, user.getRole());
    }

    public ResponseDto createUser(UserCreateDto userCreateDto) throws CustomException,
            AuthenticationFailException {

        User user = new User(userCreateDto.getUsername(), userCreateDto.getEmail(),
                userCreateDto.getRole(), userCreateDto.getPassword() );
        try {
            userRepository.save(user);
            return new ResponseDto(ResponseStatus.success.toString(), MessageStrings.USER_CREATED);
        } catch (Exception e) {
            // handle user creation fail error
            throw new CustomException(e.getMessage());
        }

    }

    boolean canCrudUser(Role role) {
        if (role == Role.admin) {
            return true;
        }
        return false;
    }

    boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // admin and manager can crud any user
        if (role == Role.admin) {
            return true;
        }
        // user can update his own record, but not his role
        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
            return true;
        }
        return false;
    }

}
