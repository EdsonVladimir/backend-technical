package com.esosa.backend.login;

import com.esosa.backend.modules.user.dao.UserDao;
import com.esosa.backend.service.JwtUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class LoginRest {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    UserDao iUserDao;

    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(LoginRest.class);

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq authenticationReq) {
       // System.out.println(authenticationReq.getEmail());
        Map<String, Object> messages = new HashMap<>();
        Object data = null;
        logger.info("Authenticating the user {}", authenticationReq.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getEmail(),
                        authenticationReq.getPass()));
        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getEmail());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);
        //System.out.println(authenticationReq.getEmail());

         data = iUserDao.userRes(authenticationReq.getEmail());
       // return ResponseEntity.ok(tokenInfo);
        messages.put("message","Has login successfully");
        messages.put("jwtToken", tokenInfo.getJwtToken());
        messages.put("user", data);
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    }
}
