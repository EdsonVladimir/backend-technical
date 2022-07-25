
/*package com.esosa.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .httpBasic(withDefaults())
       .authorizeRequests()
               .antMatchers("/public/**").permitAll()
               .antMatchers("/api/**").hasAnyRole("ADMIN")
               .anyRequest().authenticated();

   }
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
   auth
               .inMemoryAuthentication()
               .withUser("esosa").password("{noop}" + "secreto").roles("USER")
               .and()
               .withUser("mlopes").password("{noop}" + "secreto").roles("ADMIN");
   }
}
*/