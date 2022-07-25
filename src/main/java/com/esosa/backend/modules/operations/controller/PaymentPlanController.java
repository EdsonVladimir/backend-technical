package com.esosa.backend.modules.operations.controller;

import com.esosa.backend.modules.operations.dao.PaymentPlanDao;
import com.esosa.backend.modules.operations.entities.PaymentPlan;
import com.esosa.backend.modules.operations.entities.PaymentPlanModify;
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
public class PaymentPlanController {
    @Autowired
    PaymentPlanDao iPaymentPlanDao;

    @GetMapping("/payment")
    ResponseEntity<?> paimentPlanList(){
        List<PaymentPlan> data = null;
        Map<String, Object> messages = new HashMap<>();
        try{
            data = iPaymentPlanDao.paimentPlanList();
        } catch (DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<PaymentPlan>>(data, HttpStatus.OK);
    };
    @PostMapping("/payment")
    ResponseEntity<?> createPaimentPlan(@Valid @RequestBody PaymentPlanModify data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iPaymentPlanDao.createPaimentPlan(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been created successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @PutMapping("/payment")
    ResponseEntity<?> updatePaimentPlan(@Valid @RequestBody PaymentPlan data, BindingResult resultado){
        Map<String, Object> messages = new HashMap<>();
        if (resultado.hasErrors()) {
            List<String> error = resultado.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
            messages.put("error", error);
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.BAD_REQUEST);
        }
        try{
            iPaymentPlanDao.updatePaimentPlan(data);
        } catch (
                DataAccessException e){
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message","Has been modified successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.CREATED);
    };
    @DeleteMapping(value = "/payment/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> messages = new HashMap<>();
        try {
            iPaymentPlanDao.deletePaimentPlan(id);
        } catch (DataAccessException e) {
            messages.put("message", "Error when performing the query in the Database");
            messages.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.put("message", "Has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(messages, HttpStatus.OK);
    }
}
