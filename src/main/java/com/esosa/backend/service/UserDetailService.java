package com.esosa.backend.service;

import com.esosa.backend.modules.user.dao.UserDao;
import com.esosa.backend.modules.user.entities.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserDao iUserDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      // System.out.println(username);
        UserLogin email = iUserDao.user(username);
       // System.out.println(email);
        if (email == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        } else {
                User.UserBuilder userBuilder = User.withUsername(username);
           // System.out.println(email);
                // "secreto" => [BCrypt] => $2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq
              //  String encryptedPassword = "$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq";
                userBuilder.password(email.getPass()).roles(email.getEmail());
                return userBuilder.build();
          /*  } else {
                throw new UsernameNotFoundException(username);
            }*/
        }
    }
}
