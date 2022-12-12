package com.kob.botruningsystem.controller;

import com.kob.botruningsystem.service.BotRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/bot")
public class BotRunningController {

    @Autowired
    private BotRunningService botRunningService;

    @PostMapping("/add")
    public String addBot(@RequestParam MultiValueMap<String, String> data) {
        Integer userId = Integer.parseInt(Objects.requireNonNull(data.getFirst("userId")));
        String botCode = Objects.requireNonNull(data.getFirst("botCode"));
        String input = Objects.requireNonNull(data.getFirst("input"));
        return botRunningService.addBot(userId, botCode, input);
    }



}
