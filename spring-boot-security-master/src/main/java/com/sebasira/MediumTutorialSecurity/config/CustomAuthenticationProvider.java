package com.sebasira.MediumTutorialSecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sebasira.MediumTutorialSecurity.model.Role;
import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.service.UserService;

/**
 * @author Chaturanand Yadav
 * @version 1.0.0
 * @since 2020-01-14
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider() {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();

        User attempUser = userService.findUserByEmail(username);

        if (attempUser == null || !username.equals(attempUser.getEmail())) {
            throw new BadCredentialsException("Username not found.");
        }

        // Invalid Password
        if (!bCryptPasswordEncoder.matches(rawPassword, attempUser.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }


        if(username.equals(attempUser.getEmail())  && bCryptPasswordEncoder.matches(rawPassword, attempUser.getPassword())) {
            // User NOT ACTIVE
            if (attempUser.getActive() != 1){
                throw new BadCredentialsException("Usuario Inactivo");
            }

            List<Role> userRoles = new ArrayList<Role>(attempUser.getRoles());

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            for (int i=0; i<userRoles.size(); i++){
                grantedAuths.add(new SimpleGrantedAuthority(userRoles.get(i).getRole()));
            }

            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
        } else {
          
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}