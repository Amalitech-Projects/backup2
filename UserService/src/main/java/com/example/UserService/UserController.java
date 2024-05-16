package com.example.UserService;

import com.example.UserService.Dtos.UserDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public List<UserCustomResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/fname/{firstName}")
    public List<UserCustomResponse> getUserByFirstName(@PathVariable String firstName){
        return userService.getUserByFirstName(firstName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtos> updateUserDetails(@RequestParam String id, @RequestBody UserDtos userBody){
        return ResponseEntity.ok(userService.updateUserDetails(id, userBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Successfully Deleted User");
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> changePassword(@PathVariable String id){
        return ResponseEntity.ok("Request Sent Successfully");
    }

}
