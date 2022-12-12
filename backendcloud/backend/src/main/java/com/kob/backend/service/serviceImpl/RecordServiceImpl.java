package com.kob.backend.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kob.backend.common.PageUtils;
import com.kob.backend.common.Query;
import com.kob.backend.dto.RecordDTO;
import com.kob.backend.entity.Record;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.service.IRecordService;
import com.kob.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Resource
    private IUserService userService;

    @Override
    public JSONObject pageList(Map<String, Object> params) {
        LambdaQueryWrapper<Record> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Record::getCreateTime);

        IPage<Record> page = this.page(new Query<Record>().getPage(params), lqw);

        JSONObject jsonObject = new JSONObject();
        PageUtils<Record> pageUtils = new PageUtils<>(page);
        jsonObject.putOpt("currPage", pageUtils.getCurrPage());
        jsonObject.putOpt("pageSize", pageUtils.getPageSize());
        jsonObject.putOpt("totalPage", pageUtils.getTotalPage());
        jsonObject.putOpt("totalCount", pageUtils.getTotalCount());

        List<Record> list = pageUtils.getList();
        List<RecordDTO> recordDTOList = new ArrayList<>();
        for (Record record : list) {
            User userA = userService.getById(record.getAId());
            User userB = userService.getById(record.getBId());
            RecordDTO recordDTO = BeanUtil.copyProperties(record, RecordDTO.class);
            recordDTO.setUserA(userA);
            recordDTO.setUserB(userB);
            recordDTOList.add(recordDTO);
        }

        jsonObject.putOpt("list", recordDTOList);
        return jsonObject;
    }

}
