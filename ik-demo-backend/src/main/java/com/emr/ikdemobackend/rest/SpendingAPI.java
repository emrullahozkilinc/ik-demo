package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.spending.CreateSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spendings")
public class SpendingAPI {


    @GetMapping
    public ResponseEntity<List<SpendingDTO>> getAllSpendings(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<SpendingDTO> addSpending(@Valid @RequestBody CreateSpendingDTO spending){
        return ResponseEntity.ok(new SpendingDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpendingDTO> updateSpending(@PathVariable Long id,
                                                      @Valid @RequestBody CreateSpendingDTO spending){
        return ResponseEntity.ok(new SpendingDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpending(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
