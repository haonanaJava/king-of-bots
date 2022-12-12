package com.kob.backend.service.serviceImpl;

import cn.hutool.log.Log;
import com.kob.backend.comsumer.WebSocketServer;
import com.kob.backend.service.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImpl implements StartGameService {

    Log log = Log.get();

    @Override
    public String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId) {
        WebSocketServer.startGame(aId, aBotId, bId, bBotId);
        return null;
    }
}
