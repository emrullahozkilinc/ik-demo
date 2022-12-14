package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import com.emr.ikdemobackend.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/employees")
@RequiredArgsConstructor
public class EmployeeAPI {

    private final EmployeeService service;
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody RequestEmployeeDTO employee){
        return ResponseEntity.ok(service.addEmployee(employee));
    }

    @PutMapping("/{nationalId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long nationalId,
                                                      @Valid @RequestBody RequestEmployeeDTO employee){
        return ResponseEntity.ok(service.updateEmployee(nationalId,employee));
    }

    @DeleteMapping("/{nationalId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long nationalId){
        service.deleteEmployee(nationalId);
        return ResponseEntity.ok("Employee Deleted");
    }
}
