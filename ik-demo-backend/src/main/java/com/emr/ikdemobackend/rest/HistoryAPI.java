package com.emr.ikdemobackend.rest;

import com.emr.ikdemobackend.dto.response.HistoryDTO;
import com.emr.ikdemobackend.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/app/history")
@RequiredArgsConstructor
public class HistoryAPI {

    private final HistoryService service;

    @GetMapping
    public Set<HistoryDTO> getHistories(){
        return service.getHistory();
    }
}
