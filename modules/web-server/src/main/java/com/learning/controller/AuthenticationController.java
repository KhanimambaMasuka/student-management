package com.learning.controller;

import com.learning.User;
import com.learning.dto.LoginDTO;
import com.learning.dto.RegisterUserDTO;
import com.learning.dto.UserDTO;
import com.learning.dto.UserDTOMapper;
import com.learning.controller.exchange.LoginResponse;
import com.learning.service.AuthenticationService;
import com.learning.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public AuthenticationController(JwtService jwtService
            , AuthenticationService authenticationService
            , UserDTOMapper userDTOMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(userDTOMapper.toUserDTO(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginDTO) {
        User authenticatedUser = authenticationService.authenticate(loginDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }
}