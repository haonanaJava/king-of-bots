package com.kob.backend.controller;

import com.kob.backend.common.PageUtils;
import com.kob.backend.common.R;
import com.kob.backend.entity.User;
import com.kob.backend.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rankList")
public class RankListController {

    @Autowired
    private RankListService rankListService;

    @GetMapping("/toplist")
    public R<PageUtils<User>> getTopList(@RequestParam Map<String, Object> params) {
        return R.ok(rankListService.getRankList(params));
    }

}
