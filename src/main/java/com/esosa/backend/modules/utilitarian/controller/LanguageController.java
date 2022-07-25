package com.esosa.backend.modules.utilitarian.controller;

import com.esosa.backend.modules.utilitarian.dao.LanguageDao;
import com.esosa.backend.modules.utilitarian.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class LanguageController {
    @Autowired
    LanguageDao iLanguageDao;

    @GetMapping("/language")
    ResponseEntity<?> countryList(){
        List<Language> data = null;
        Map<String, Object> messages = new HashMap<>();
        try{
            data = iLanguageDao.languageList();
        } catch (DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Language>>(data, HttpStatus.OK);
    };
    @PostMapping("/language")
    ResponseEntity<?> createUserInit(@Valid @RequestBody Language data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iLanguageDao.createLanguage(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @PutMapping("/language")
    ResponseEntity<?> updateUser(@Valid @RequestBody Language data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iLanguageDao.updateLanguage(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @DeleteMapping(value = "/language/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iLanguageDao.deleteLanguage(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    }
}
