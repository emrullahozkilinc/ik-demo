package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.service.SpendingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/spendings")
@RequiredArgsConstructor
public class SpendingAPI {

    private final SpendingService service;

    @GetMapping
    public ResponseEntity<List<SpendingDTO>> getAllSpendings(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<SpendingDTO> addSpending(@Valid @RequestBody RequestSpendingDTO spending){
        return ResponseEntity.ok(service.addSpending(spending));
    }

    @PutMapping("/{userNationalId}")
    public ResponseEntity<SpendingDTO> updateSpending(@PathVariable Long userNationalId,
                                                      @Valid @RequestBody RequestSpendingDTO spending){
        return ResponseEntity.ok(service.updateSpending(userNationalId, spending));
    }

    @DeleteMapping("/{userNationalId}")
    public ResponseEntity<String> deleteSpending(@PathVariable Long userNationalId){
        service.deleteSpending(userNationalId);
        return ResponseEntity.ok("Spending deleted.");
    }
}
