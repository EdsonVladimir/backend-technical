package com.esosa.backend.modules.courses.controller;

import com.esosa.backend.modules.courses.dao.CourseOperationDao;
import com.esosa.backend.modules.courses.entities.Course;
import com.esosa.backend.modules.courses.entities.CourseOperation;
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
@RequestMapping("/api/courses")
public class CurseOperationController {
    @Autowired
    CourseOperationDao iCourseOperationDao;

    @PostMapping
    ResponseEntity<?> createCourse(@Valid @RequestBody CourseOperation data, BindingResult resultado){

    Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
       try{
        iCourseOperationDao.createCourse(data);
    } catch (
    DataAccessException e){
        messages.put("message", "Error when performing the query in the Database");
        messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
    }
        messages.put("message","Has been created successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);

    };
    @PutMapping
    ResponseEntity<?> updateCourse(@Valid @RequestBody Course data, BindingResult resultado){

        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iCourseOperationDao.updateCourse(data);
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
    ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iCourseOperationDao.deleteCourse(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    };
}
