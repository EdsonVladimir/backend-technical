package com.esosa.backend.login;

import com.esosa.backend.service.JwtUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/public")
public class LoginRest {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(LoginRest.class);

    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
       // System.out.println(authenticationReq.getEmail());
        logger.info("Authenticating the user {}", authenticationReq.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getEmail(),
                        authenticationReq.getPass()));
        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getEmail());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }
}
