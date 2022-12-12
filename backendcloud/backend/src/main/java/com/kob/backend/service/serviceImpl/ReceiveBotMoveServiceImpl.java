package com.kob.backend.service.serviceImpl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import com.kob.backend.comsumer.Game;
import com.kob.backend.comsumer.WebSocketServer;
import com.kob.backend.service.ReceiveBotMoveService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveBotMoveServiceImpl implements ReceiveBotMoveService {


    private final static Log log = Log.get();

    @Override
    public String receiveBotMove(Long userId, Integer direction) {
        if (ObjectUtil.isNotNull(WebSocketServer.users.get(userId))) {
            Game game = WebSocketServer.users.get(userId).game;
            if (ObjectUtil.isNotNull(game)) {
                if (game.getPlayerA().getId().equals(userId)) {
                    if (!game.getPlayerA().getBotId().equals(-1L))
                        game.setNextStepA(direction);
                } else if (game.getPlayerB().getId().equals(userId)) {
                    if (!game.getPlayerB().getBotId().equals(-1L))
                        game.setNextStepB(direction);
                }
            }
        }
        return "receive bot move success";
    }


}
