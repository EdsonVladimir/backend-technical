package com.esosa.backend.modules.user.controller;

import com.esosa.backend.modules.user.dao.UserDao;
import com.esosa.backend.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
   @Autowired
    UserDao iUserDao;

   @GetMapping(value="/user")
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
   }

}
