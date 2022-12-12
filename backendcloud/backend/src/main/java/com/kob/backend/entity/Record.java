package com.kob.backend.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("record")
public class Record {

    @TableId
    private Long id;

    @TableField("a_id")
    private Long aId;

    @TableField("a_sx")
    private Integer aSx;

    @TableField("a_sy")
    private Integer aSy;

    @TableField("b_id")
    private Long bId;

    @TableField("b_sx")
    private Integer bSx;

    @TableField("b_sy")
    private Integer bSy;

    @TableField("a_steps")
    private String aSteps;

    @TableField("b_steps")
    private String bSteps;

    @TableField("map")
    private String map;

    @TableField("loser")
    private String loser;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;
}
