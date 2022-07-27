package com.esosa.backend.modules.user.controller;

import com.esosa.backend.modules.user.dao.UserDao;
import com.esosa.backend.modules.user.entities.User;
import com.esosa.backend.modules.user.entities.UserRegInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
   @Autowired
   UserDao iUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
   @GetMapping("/user")
    ResponseEntity<?> usersList(){
       List<User> data = null;
       Map<String, Object> messages = new HashMap<>();
       try{
           data = iUserDao.usersList();
       } catch (DataAccessException e){
           messages.put("message", "Error when performing the query in the Database");
           messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
           return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity<List<User>>(data, HttpStatus.OK);
   };
    @PostMapping("/user")
    ResponseEntity<?> createUserInit(@Valid @RequestBody UserRegInit data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            data.setPass(passwordEncoder.encode(data.getPass()));
            iUserDao.createUserInit(data);
        } catch (DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been created successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @PutMapping("/user")
    ResponseEntity<?> updateUser(@Valid @RequestBody User data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iUserDao.updateUser(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iUserDao.deleteUser(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    }

}
