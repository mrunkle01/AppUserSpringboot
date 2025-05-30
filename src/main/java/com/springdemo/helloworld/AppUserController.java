package com.springdemo.helloworld;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AppUserController {
    private final AppUserService appUserService;
    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }
    @PostMapping("/user/add")
    public String addUser(@Valid @RequestBody AppUser user){
        appUserService.addUser(user);
        return "User added";
    }

    @GetMapping("/user/get/{email}")
    public ResponseEntity<AppUser> getUser(@PathVariable String email){
        AppUser user = appUserService.getUser(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping("/user/update/{email}")
    public ResponseEntity<String> update(@Valid @RequestBody AppUser user,
                                         @PathVariable String email){
        if (!appUserService.exist(email)){
            return ResponseEntity.notFound().build();
        }
        else{
            appUserService.updateUser(user, email);
            return ResponseEntity.ok("User Updated");
        }
    }
    @DeleteMapping("/user/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        if (appUserService.exist(email)){
            appUserService.remove(email);
            return ResponseEntity.ok("User Deleted");
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    @GetMapping("/users")
    public Collection<AppUser> getUsers(){
        //test purpose only
        return appUserService.getUsers();

    }
    @GetMapping("/ping")
    public String ping() {
        return "pong from Spring Boot!";
    }

}


