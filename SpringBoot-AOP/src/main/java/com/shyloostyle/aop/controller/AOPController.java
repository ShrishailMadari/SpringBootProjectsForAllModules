package com.shyloostyle.aop.controller;

import com.shyloostyle.aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AOPController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get")
    public String getTheMessage(){
        return "hello Aop World";
    }

    @GetMapping("/add")
    public ResponseEntity<String> getProduct(String product){
        product = "Hey Aop";
       return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping(path = "/fetchEmployee")
    public ResponseEntity<String> getEmployee(){
        employeeService.employeeService();
        return new ResponseEntity<>("fetched Employee",HttpStatus.OK);
    }
}
