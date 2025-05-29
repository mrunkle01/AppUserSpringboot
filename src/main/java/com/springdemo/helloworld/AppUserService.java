package com.springdemo.helloworld;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void addUser(AppUser user){
        appUserRepository.save(user);
    }
    public AppUser getUser(String email){
        return appUserRepository.findByEmail(email).orElse(null);
    }
    @Transactional
    public void updateUser(AppUser user,String email){
        AppUser existingUser = appUserRepository.findByEmail(email)
                                            .orElse(null);
        if(existingUser != null){
            existingUser.setAge(user.getAge());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
        }
    }
    public boolean exist(String email){
        return appUserRepository.existsByEmail(email);
    }
    @Transactional
    public void remove(String email){
        appUserRepository.deleteByEmail(email);
    }
    public Collection<AppUser> getUsers(){
        return appUserRepository.findAll();
    }
}
