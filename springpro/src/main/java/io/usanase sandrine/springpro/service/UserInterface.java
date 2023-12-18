package io.cedricksonga.springpro.service;

import io.cedricksonga.springpro.beans.UserRegistrationBean;
import io.cedricksonga.springpro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInterface extends UserDetailsService {
    public User saveAccount(UserRegistrationBean userRegistrationBean);
    public User findByEmail(String email);
}
