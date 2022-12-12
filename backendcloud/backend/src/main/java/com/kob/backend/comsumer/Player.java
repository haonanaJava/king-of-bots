package com.kob.backend.comsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Long id;

    private Integer sx;

    private Integer sy;

    // -1 表示亲自出马，其他数字表示机器人id
    private Long botId;

    private String botCode;


    /**
     * 表示每一步的方向, 记录玩家的移动轨迹
     */
    private List<Integer> steps;

    private boolean checkTailIncreasing(int step) {
        if (step <= 10) return true;
        else return step % 3 == 1;
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        for (int d : steps) {
            x += dx[d];
            y += dy[d];
            cells.add(new Cell(x, y));
            if (!checkTailIncreasing(++step)) {
                cells.remove(0);
            }
        }
        return cells;
    }

    public String getStepsString() {
        StringBuilder s = new StringBuilder();
        for (int d : steps) {
            s.append(d);
        }
        return s.toString();
    }
}
