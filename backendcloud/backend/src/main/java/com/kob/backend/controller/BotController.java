package com.kob.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kob.backend.common.PageUtils;
import com.kob.backend.common.Query;
import com.kob.backend.common.R;
import com.kob.backend.common.SecurityUtils;
import com.kob.backend.dto.AddValid;
import com.kob.backend.dto.BotParam;
import com.kob.backend.dto.EditValid;
import com.kob.backend.entity.Bot;
import com.kob.backend.entity.User;
import com.kob.backend.service.IBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    private IBotService botService;

    @PostMapping("/add")
    public R<String> add(@Validated(AddValid.class) @RequestBody BotParam botParam) {
        Bot bot = BeanUtil.copyProperties(botParam, Bot.class);
        bot.setUserId(SecurityUtils.getUser().getId());
        botService.save(bot);
        return R.ok("添加成功");
    }

    @GetMapping("/{id}")
    public R<Bot> get(@PathVariable Long id) {
        Bot bot = botService.getById(id);
        return R.ok(bot);
    }


    @DeleteMapping("/remove/{id}")
    public R<String> remove(@PathVariable Integer id) {
        User user = SecurityUtils.getUser();
        Bot bot = botService.getById(id);
        if (!ObjectUtil.equal(bot.getUserId(), user.getId())) {
            return R.fail("没有操作权限");
        }
        boolean b = botService.removeById(id);
        if (b) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }

    }


    @PutMapping("/update")
    public R<String> update(@Validated(EditValid.class) @RequestBody BotParam botParam) {
        Bot bot = BeanUtil.copyProperties(botParam, Bot.class);
        boolean b = botService.updateById(bot);
        if (b) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }

    @GetMapping("/list")
    public R<PageUtils<Bot>> list(@RequestParam Map<String, Object> params) {
        LambdaQueryWrapper<Bot> lqw = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotNull(params.get("title"))) {
            lqw.like(Bot::getTitle, params.get("title"));
        }
        User user = SecurityUtils.getUser();
        Long id = user.getId();
        lqw.eq(Bot::getUserId, id);
        IPage<Bot> page = botService.page(new Query<Bot>().getPage(params), lqw);
        return R.ok(new PageUtils<>(page));
    }

}
