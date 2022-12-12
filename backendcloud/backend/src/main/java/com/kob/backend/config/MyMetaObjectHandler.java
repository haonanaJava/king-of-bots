package com.kob.backend.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Instant instant = Instant.now();
        Date date = Date.from(instant);

        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
        this.setFieldValByName("logic", 0, metaObject);
        this.setFieldValByName("rating", 1500, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Instant instant = Instant.now();
        Date date = Date.from(instant);
        this.setFieldValByName("updateDate", date, metaObject);
    }
}
