package ru.kata.spring.boot_security.demo.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Component
public class AuthProviderImpl  implements AuthenticationProvider {
    private  final UserDetailsService userDetailsService;
    private final UserServiceImpl userService;

    public AuthProviderImpl(UserDetailsService userDetailsService, UserServiceImpl userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())){
            throw  new BadCredentialsException("Incorrect password :(");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password,
                userService.getUserAuthorities(username));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
