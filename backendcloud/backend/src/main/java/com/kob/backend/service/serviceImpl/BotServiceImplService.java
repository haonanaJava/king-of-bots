package com.kob.backend.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kob.backend.entity.Bot;
import com.kob.backend.mapper.BotMapper;
import com.kob.backend.service.IBotService;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImplService extends ServiceImpl<BotMapper, Bot> implements IBotService {
}
