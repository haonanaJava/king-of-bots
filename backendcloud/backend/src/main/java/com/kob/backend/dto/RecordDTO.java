package com.kob.backend.dto;


import com.kob.backend.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class RecordDTO {


    private Long id;

    private Long aId;

    private Integer aSx;

    private Integer aSy;

    private Long bId;

    private Integer bSx;

    private Integer bSy;

    private String aSteps;

    private String bSteps;

    private String map;

    private String loser;

    private Date createTime;

    private User userA;

    private User userB;



}
