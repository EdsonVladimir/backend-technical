package com.esosa.backend.modules.operations.controller;

import com.esosa.backend.modules.operations.dao.EnrolledCourseDao;
import com.esosa.backend.modules.operations.entities.EnrolledCourse;
import com.esosa.backend.modules.operations.entities.EnrolledCourseUser;
import com.esosa.backend.modules.operations.entities.EnrollerCourseCreate;
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
public class EnrolledCourseController {
    @Autowired
    EnrolledCourseDao iEnrolledCourseDao;

    @GetMapping("/enrolled-user/{id}")
    ResponseEntity<?> enrolledCourseList(@PathVariable Long id){
        List<EnrolledCourseUser> data = null;
        Map<String, Object> messages = new HashMap<>();
        try{
            data = iEnrolledCourseDao.enrolledCourseList(id);
        } catch (DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<EnrolledCourseUser>>(data, HttpStatus.OK);
    };
    @PostMapping("/enrolled")
    ResponseEntity<?> createEnrolledCourse(@Valid @RequestBody EnrollerCourseCreate data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{

            iEnrolledCourseDao.createEnrolledCourse(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been created successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @PutMapping("/enrolled")
    ResponseEntity<?> updateEnrolledCourse(@Valid @RequestBody EnrolledCourse data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iEnrolledCourseDao.updateEnrolledCourse(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @DeleteMapping(value = "/enrolled/{id}")
    ResponseEntity<?> deleteEnrolledCourse(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iEnrolledCourseDao.deleteEnrolledCourse(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    }
}
