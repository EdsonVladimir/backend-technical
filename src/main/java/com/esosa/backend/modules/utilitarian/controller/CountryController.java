package com.esosa.backend.modules.utilitarian.controller;

import com.esosa.backend.modules.utilitarian.dao.CountryDao;
import com.esosa.backend.modules.utilitarian.entities.Country;
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
public class CountryController {
   @Autowired
   CountryDao iCountryDao;

    @GetMapping("/country")
    ResponseEntity<?> countryList(){
        List<Country> data = null;
        Map<String, Object> messages = new HashMap<>();
        try{
            data = iCountryDao.countryList();
        } catch (DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Country>>(data, HttpStatus.OK);
    };
    @PostMapping("/country")
    ResponseEntity<?> createUserInit(@Valid @RequestBody Country data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iCountryDao.createCountry(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @PutMapping("/country")
    ResponseEntity<?> updateUser(@Valid @RequestBody Country data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iCountryDao.updateCountry(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @DeleteMapping(value = "/country/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iCountryDao.deleteCountry(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    }
}
