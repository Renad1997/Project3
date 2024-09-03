package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<User> getAllUsers() {
        return authRepository.findAll();
    }

    public void register(User user) {
        user.setRole("CUSTOMER");
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public void updateUser(Integer id,User user){

        User user1=authRepository.findUserById(id);
        if(user1==null){
            throw new ApiException("User not found");
        }
        user1.setUsername(user.getUsername());
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);
        authRepository.save(user1);
    }

    public void deleteUser(Integer id){
        User user1=authRepository.findUserById(id);
        if(user1==null){
            throw new ApiException("User not found");
        }
        authRepository.delete(user1);
    }



}
