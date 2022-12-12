package com.kob.matchingsystem.service.impl;

import cn.hutool.log.Log;
import com.kob.matchingsystem.core.MatchingPool;
import com.kob.matchingsystem.service.MatchingService;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {

    public final static MatchingPool matchingPool = new MatchingPool();

    private final Log log = Log.get();


    @Override
    public String addPlayer(Integer userId, Integer rating, Integer botId) {
        matchingPool.addPlayer(userId, rating, botId);
        return null;
    }


    @Override
    public String removePlayer(Integer userId) {
        matchingPool.removePlayer(userId);
        return null;
    }
}
