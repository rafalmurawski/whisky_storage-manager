package com.example.whisky_base.controller;

import com.example.whisky_base.service.TempService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/start")
@RestController
public class TempApi {

    TempService tempService;


    public TempApi(TempService tempService) {
        this.tempService = tempService;
    }

    @GetMapping
    public void addList() {
        tempService.addStartDistilleries();
    }
}
