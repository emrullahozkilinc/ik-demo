package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/shifts")
public class ShiftAPI {

    @GetMapping
    public ResponseEntity<List<ShiftDTO>> getAllShifts(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<ShiftDTO> addShift(@Valid @RequestBody RequestShiftDTO shift){
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftDTO> updateShift(@PathVariable Long id,
                                                      @Valid @RequestBody RequestShiftDTO shift){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShift(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
