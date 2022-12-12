package com.kob.backend.controller;

import com.kob.backend.service.ReceiveBotMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/receiveBotMove")
public class ReceiveBotMoveController {

    @Autowired
    private ReceiveBotMoveService receiveBotMoveService;

    @PostMapping("/move")
    public String receiveBotMove(@RequestParam MultiValueMap<String, String> params) {
        Long userId = Long.parseLong(Objects.requireNonNull(params.getFirst("userId")));
        Integer direction = Integer.parseInt(Objects.requireNonNull(params.getFirst("direction")));
        return receiveBotMoveService.receiveBotMove(userId, direction);
    }

}
