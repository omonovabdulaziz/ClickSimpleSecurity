package uz.pdp.uyvazifacard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyAuthService implements UserDetailsService {
    @Autowired
     PasswordEncoder passwordEncoder;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       List<User>  userList = new ArrayList<>(
                Arrays.asList(
                        new User("abdulaziz", passwordEncoder.encode("abdulaziz"), new ArrayList<>()),
                        new User("aziz", passwordEncoder.encode("aziz"), new ArrayList<>()),
                        new User("eshmat", passwordEncoder.encode("eshmat"), new ArrayList<>())

                )
        );
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

}


