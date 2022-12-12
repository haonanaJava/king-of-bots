package com.kob.botruningsystem.service.impl;

import cn.hutool.log.Log;
import com.kob.botruningsystem.core.BotPool;
import com.kob.botruningsystem.service.BotRunningService;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {

    public final static BotPool botPool = new BotPool();

    private final Log log = Log.get();

    @Override
    public String addBot(Integer userId, String botCode, String input) {
        botPool.addBot(userId, botCode, input);
        return "add bot success";
    }

}
