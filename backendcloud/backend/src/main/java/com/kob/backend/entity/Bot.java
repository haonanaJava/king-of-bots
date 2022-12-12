package com.kob.backend.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("bot")
@Data
public class Bot {

    @TableId
    private Long id;

    @TableField("user_id")
    private Long userId;

    private String title;

    private String description;

    private String content;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "logic", fill = FieldFill.INSERT)
    private Integer logic;

}
