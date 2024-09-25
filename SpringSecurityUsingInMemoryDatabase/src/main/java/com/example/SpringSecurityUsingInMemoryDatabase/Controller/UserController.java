package com.example.SpringSecurityUsingInMemoryDatabase.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.SpringSecurityUsingInMemoryDatabase.JWTSecurity.JwtUtil;
import com.example.SpringSecurityUsingInMemoryDatabase.Modal.User;
import com.example.SpringSecurityUsingInMemoryDatabase.Services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController  {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;
    
    @Operation(summary = "signin all users", description = "signin all users")
    @ApiResponses(value= {@ApiResponse(responseCode="200",description="Successful")})
    @PostMapping("/signin")
    public ResponseEntity<?> createToken(@RequestParam String username, @RequestParam String password) {
    	System.err.println("gfgfdg1");
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        System.err.println("gfgfd2g");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
    
    @GetMapping("/signin")
    public ResponseEntity<?> createTokens() {
       System.err.println("gfgfdg");
        return ResponseEntity.ok("ok");
    }
    
    @Operation(summary = "get all users", description = "get all users")
    @ApiResponses(value= {@ApiResponse(responseCode="200",description="Successful")})
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
       System.err.println("gfgfdg");
       
        return ResponseEntity.ok(userService.getUsers());
    }
    
    
    @Operation(summary = "register all users", description = "register all users")
    @ApiResponses(value= {@ApiResponse(responseCode="200",description="Successful")})
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User request) {
		/*
		 * User user = new User(); user.setUsername(request.getUsername());
		 * user.setPassword(request.getPassword()); user.setRoles(request.getRoles());
		 */
        return ResponseEntity.ok(userService.register(request));
    }
}
