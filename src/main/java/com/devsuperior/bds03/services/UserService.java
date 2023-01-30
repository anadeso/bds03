package com.devsuperior.bds03.services;

import com.devsuperior.bds03.entities.User;
import com.devsuperior.bds03.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByEmail(s);

        if (user == null) {
            logger.error("User not found: " + s);
            throw new UsernameNotFoundException("Email not found");
        }

        logger.info("User found: " + s);
        return user;
    }
}
