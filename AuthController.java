//package com.example.blogBipin.controller;
//
//import com.example.blogBipin.Dto.UserDto;
////import com.example.blogBipin.services.impl.UserServiceImpl;
//import com.example.blogBipin.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Invalid username or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return ResponseEntity.ok(jwt);
//    }
//}