package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/dayoffs")
public class DayoffAPI {


    @GetMapping
    public ResponseEntity<List<DayoffDTO>> getAllDayoffs(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<DayoffDTO> addDayoff(@Valid @RequestBody RequestDayoffDTO dayoff){
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DayoffDTO> updateDayoff(@PathVariable Long id,
                                                      @Valid @RequestBody RequestDayoffDTO dayoff){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDayoff(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
