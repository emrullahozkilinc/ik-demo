package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.service.DayoffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/dayoffs")
@RequiredArgsConstructor
public class DayoffAPI {

    private final DayoffService service;

    @GetMapping
    public ResponseEntity<List<DayoffDTO>> getAllDayoffs(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<DayoffDTO> addDayoff(@Valid @RequestBody RequestDayoffDTO dayoff){
        return ResponseEntity.ok(service.addDayoff(dayoff));
    }

    @PutMapping("/{userNationalId}")
    public ResponseEntity<DayoffDTO> updateDayoff(@PathVariable Long userNationalId,
                                                      @Valid @RequestBody RequestDayoffDTO dayoff){
        return ResponseEntity.ok(service.updateDayoff(userNationalId, dayoff));
    }

    @DeleteMapping("/{userNationalId}")
    public ResponseEntity<String> deleteDayoff(@PathVariable Long userNationalId){
        service.deleteDayoff(userNationalId);
        return ResponseEntity.ok("Dayoff Deleted");
    }
}
