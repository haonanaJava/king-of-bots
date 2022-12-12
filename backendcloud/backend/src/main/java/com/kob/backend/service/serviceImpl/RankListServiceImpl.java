package com.kob.backend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kob.backend.common.PageUtils;
import com.kob.backend.common.Query;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.RankListService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RankListServiceImpl extends ServiceImpl<UserMapper, User> implements RankListService {

    @Override
    public  PageUtils<User> getRankList(Map<String, Object> params) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(User::getRating);
        IPage<User> page = this.page(new Query<User>().getPage(params), qw);
        return new PageUtils<>(page);
    }

}
