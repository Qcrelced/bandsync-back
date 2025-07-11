package com.selecao.bandsyncback.core.security;

import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), new ArrayList<>());
        }
    }
}
