package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.employee.CreateEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI {

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody CreateEmployeeDTO employee){
        return ResponseEntity.ok(new EmployeeDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                      @Valid @RequestBody CreateEmployeeDTO employee){
        return ResponseEntity.ok(new EmployeeDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
