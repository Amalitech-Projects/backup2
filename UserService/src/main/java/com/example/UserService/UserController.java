package com.example.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String getStart(){
        return "Works";
    }

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

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDtos> updateUserDetails(@RequestParam String id, @RequestBody UserDtos userBody){
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Successfully Deleted User");
    }

}
