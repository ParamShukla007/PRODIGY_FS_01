package com.example.authentication.user_authentication.controllers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authentication.user_authentication.dao.UserRepository;
import com.example.authentication.user_authentication.entities.User;



public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.getUserByUserEmail(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Could not find user!!");
        }
        
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
    
}
