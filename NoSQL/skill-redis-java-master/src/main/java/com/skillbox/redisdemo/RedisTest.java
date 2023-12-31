package com.skillbox.redisdemo;

import org.redisson.api.RScoredSortedSet;

import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    private static final int SLEEP = 1000; // 1 секунда

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        RScoredSortedSet<String> onlineUsers = redis.getOnlineUsers();

        for (int i = 0; ; i++) {

            for (String user : onlineUsers) {
                if (++i % 10 == 0) {
                    String paidUser = "User " + new Random().nextInt(20);
                    redis.deleteUser(paidUser);
                    redis.logPageVisit(onlineUsers.firstScore() - 0.1, paidUser);
                    out.println(paidUser + " оплатил платную услугу");
                }
                out.println("На главной странице показываем " + user);
            }
//            out.println("Показ окончен " + redis.calculateUsersNumber());
            Thread.sleep(SLEEP);
        }
    }
}
