package com.exercise.process.Controller;

import com.exercise.process.Service.Service;
import com.exercise.process.Entity.User;
import com.exercise.process.dto.loginDTO;
import com.exercise.process.jwtAuth.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    private Service userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, String>> createUser(@RequestBody @Valid User userInput) {
        HashMap<String, String> createdUser = userService.saveUser(userInput);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody @Valid loginDTO logindto) {
        HashMap<String, String> login = userService.loginUser(logindto);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @GetMapping("/select")
    public List<User> getAllUsers() {

        return userService.getDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getUserbyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/verify")
    public ResponseEntity<HashMap<String,Object>> verifyJwt(User user) {
        HashMap<String,Object> responseMap = new HashMap<>();

        String authorisation="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJpIiwiaWF0IjoxNzIxOTc2MjM1LCJleHAiOjE3MjIwMTIyMzV9.EICit4W3bVNSuAgvIyG3kD_H9BNWzBZNhNgPpCl8H5hyOixKF0UpXxVf0uakQIIFnMrL1KE88KmmqjNZQ-WHeg";
        try {
            jwtUtil.extractAllClaims(authorisation);
            responseMap.put("data", "The token is verified and validated");
            responseMap.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("data", "Invalid token");
            responseMap.put("status", HttpStatus.UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
    }

//    @GetMapping("/{field}")
//    private ResponseEntity<List<User>> getnamewithsorting(@PathVariable String field){
//        List<User> allusers = userService.usingSorting(field);
//        return new ResponseEntity<>(allusers, HttpStatus.OK);
//
//    }

    //pagination
    @GetMapping("/pagination/{offset}/{pagesize}")
    public ResponseEntity<Page<User>> getUsersWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
        Page<User> allUsers = userService.usageOfPagination(offset, pagesize);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


}