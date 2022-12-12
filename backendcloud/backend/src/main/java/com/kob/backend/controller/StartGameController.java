package com.kob.backend.controller;

import com.kob.backend.service.StartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/startGame")
public class StartGameController {
    @Autowired
    private StartGameService startGameService;

    @PostMapping("/start")
    public String startGame(@RequestParam MultiValueMap<String, String> data) {
        Integer aId = Integer.parseInt(Objects.requireNonNull(data.getFirst("aId")));
        Integer aBotId = Integer.parseInt(Objects.requireNonNull(data.getFirst("aBotId")));
        Integer bId = Integer.parseInt(Objects.requireNonNull(data.getFirst("bId")));
        Integer bBotId = Integer.parseInt(Objects.requireNonNull(data.getFirst("bBotId")));
        return startGameService.startGame(aId, aBotId, bId, bBotId);
    }


}
