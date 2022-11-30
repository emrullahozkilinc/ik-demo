package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.dayoff.CreateDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dayoff")
public class DayoffAPI {


    @GetMapping
    public ResponseEntity<List<DayoffDTO>> getAllDayoffs(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<DayoffDTO> addDayoff(@Valid @RequestBody CreateDayoffDTO dayoff){
        return ResponseEntity.ok(new DayoffDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DayoffDTO> updateDayoff(@PathVariable Long id,
                                                      @Valid @RequestBody CreateDayoffDTO dayoff){
        return ResponseEntity.ok(new DayoffDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDayoff(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
