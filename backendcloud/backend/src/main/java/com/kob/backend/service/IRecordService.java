package com.kob.backend.service;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kob.backend.common.PageUtils;
import com.kob.backend.entity.Record;

import java.util.List;
import java.util.Map;

public interface IRecordService extends IService<Record> {


    JSONObject pageList(Map<String, Object> params);

}
