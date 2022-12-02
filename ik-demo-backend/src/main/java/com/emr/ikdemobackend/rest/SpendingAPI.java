package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/spendings")
public class SpendingAPI {

    @GetMapping
    public ResponseEntity<List<SpendingDTO>> getAllSpendings(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<SpendingDTO> addSpending(@Valid @RequestBody RequestSpendingDTO spending){
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpendingDTO> updateSpending(@PathVariable Long id,
                                                      @Valid @RequestBody RequestSpendingDTO spending){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpending(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
