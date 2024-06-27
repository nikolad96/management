package com.example.management.controller;

import com.example.management.model.User;
import com.example.management.repository.UserRepository;
import com.example.management.security.UserTokenState;
import com.example.management.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final String REGISTER_URI = "/register";
    private static final String LOGIN_URI = "/login";

    private final AuthenticationService authenticationService;

    private record LoginDto(String username, String password){};

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Register a new User", tags = {"user", "post", "single"})
    @RequestMapping(method = RequestMethod.POST, path = REGISTER_URI + "/user", produces = "application/json", consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        authenticationService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = LoginDto.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Login with JWT", tags = {"login", "post", "single"})
    @RequestMapping(method = RequestMethod.POST, path = LOGIN_URI, produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserTokenState> loginJWT(@RequestBody LoginDto loginDto){
        String jwt = authenticationService.login(loginDto.username, loginDto.password);
        int expiresIn = 3600;
        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }
}
