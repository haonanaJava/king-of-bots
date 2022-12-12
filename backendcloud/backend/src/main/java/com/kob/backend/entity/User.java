package com.kob.backend.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class User {

    @TableId
    private Long id;

    private String username;

    private String password;

    private String avatar;

    @TableField(fill = FieldFill.INSERT)
    private Integer rating;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "logic", fill = FieldFill.INSERT)
    private Integer logic;

}
