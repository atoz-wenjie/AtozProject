package net.codeJava.atozService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.codeJava.atozEntity.CustomUserDetails;
import net.codeJava.atozEntity.User;
import net.codeJava.atozRepository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

}
