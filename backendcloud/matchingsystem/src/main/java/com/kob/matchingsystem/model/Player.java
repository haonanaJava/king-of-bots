package com.kob.matchingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Integer userId;

    private Integer rating;

    private Integer waitingTime;

    private Integer botId;

}
