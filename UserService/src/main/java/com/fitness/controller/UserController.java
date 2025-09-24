package com.fitness.controller;


import com.fitness.Service.UserService;
import com.fitness.dto.RegisterRequest;
import com.fitness.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserResponseById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));

    }




    @GetMapping("/{id}/validate")
    public ResponseEntity<Boolean> validateUserByUserId(@PathVariable String id){
        log.info("Called User Service with Id: "+ id);
        return ResponseEntity.ok(userService.existsByUserId(id));
    }
}
