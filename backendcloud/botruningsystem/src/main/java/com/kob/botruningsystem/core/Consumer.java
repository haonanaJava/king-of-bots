package com.kob.botruningsystem.core;

import cn.hutool.core.lang.UUID;
import cn.hutool.log.Log;
import com.kob.botruningsystem.model.Bot;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Supplier;

@Component
public class Consumer extends Thread {

    private final Log log = Log.get();
    private Bot bot;

    private static RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeout(long timeout, Bot bot) {
        this.bot = bot;
        this.start();
        try {
            // 最多等待timeout毫秒
            this.join(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    @Override
    public void run() {
        String uid = UUID.randomUUID(true).toString().substring(0, 8);
        Supplier<Integer> botInterface = Reflect.compile(
                "com.kob.botruningsystem.reflect.Bot" + uid,
                addUid(bot.getBotCode(), uid)
        ).create().get();

        File file = new File("input.txt");
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(bot.getInput());
            pw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Integer direction = botInterface.get();

        String URL = "http://127.0.0.1:8080/receiveBotMove/move";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", bot.getUserId().toString());
        map.add("direction", direction.toString());

        restTemplate.postForObject(URL, map, String.class);
    }

    /**
     * 在code中类名后面加上uid，防止多个线程同时编译同一个类
     *
     * @param code
     * @param uid
     * @return
     */
    private String addUid(String code, String uid) {
        int k = code.indexOf(" implements Supplier<Integer>");
        return code.substring(0, k) + uid + code.substring(k);
    }

}
