
package com.esosa.backend.config;


import com.esosa.backend.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .httpBasic(withDefaults())
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
               .antMatchers("/public/**").permitAll()
               // .antMatchers("/api/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
               .anyRequest().authenticated()
               .and().cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
           /*    .csrf().disable()
       .authorizeRequests()
               .antMatchers("/public/**").permitAll()
               .antMatchers("/public/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
               .anyRequest().authenticated()
           .and().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
*/
   }
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
       auth.userDetailsService(userDetailsService);
   /*
       auth
               .inMemoryAuthentication()
               .withUser("esosa").password("{noop}" + "secreto").roles("USER")
               .and()
               .withUser("mlopes").password("{noop}" + "secreto").roles("ADMIN");

    */

   }
}
