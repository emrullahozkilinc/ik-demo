package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.service.ShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/shifts")
@RequiredArgsConstructor
public class ShiftAPI {

    private final ShiftService service;
    @GetMapping
    public ResponseEntity<List<ShiftDTO>> getAllShifts(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<ShiftDTO> addShift(@Valid @RequestBody RequestShiftDTO shift){
        return ResponseEntity.ok(service.addShift(shift));
    }

    @PutMapping("/{shiftId}")
    public ResponseEntity<ShiftDTO> updateShift(@PathVariable Long shiftId,
                                                      @Valid @RequestBody RequestShiftDTO shift){
        return ResponseEntity.ok(service.updateShift(shiftId, shift));
    }

    @DeleteMapping("/{shiftId}")
    public ResponseEntity<String> deleteShift(@PathVariable Long shiftId){
        service.deleteShift(shiftId);
        return ResponseEntity.ok("Shift Deleted");
    }
}
