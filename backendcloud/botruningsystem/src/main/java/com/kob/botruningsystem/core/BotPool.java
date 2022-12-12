package com.kob.botruningsystem.core;

import com.kob.botruningsystem.model.Bot;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BotPool extends Thread {

    private static final Queue<Bot> botPool = new LinkedList<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void addBot(Integer botId, String botCode, String input) {
        lock.lock();
        try {
            botPool.add(new Bot(botId, botCode, input));
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 比较耗时的操作
     *
     * @param bot
     */
    private void consume(Bot bot) {
        Consumer consumer = new Consumer();
        consumer.startTimeout(2000, bot);
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (botPool.isEmpty()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            } else {
                Bot bot = botPool.remove();
                lock.unlock();
                consume(bot);
            }
        }
    }


}
