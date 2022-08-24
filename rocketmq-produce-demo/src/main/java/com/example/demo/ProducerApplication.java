package com.example.demo;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @program: rocketmq-multi-instance-demo
 * @description:
 * @author: shenqi
 * @create: 2022-08-24 14:06
 */
@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    @Value("${demo.rocketmq.topic}")
    private String demoTopic;
    @Value("${demo.rocketmq.tagA}")
    private String tagA;
    @Value("${demo.rocketmq.tagB}")
    private String tagB;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String tag = tagA;
        for (int i = 0; i < 10; i++) {
            tag = i % 2 == 0 ? tagA : tagB;
            SendResult sendResult = rocketMQTemplate.syncSend(demoTopic + ":" + tag, "Hello, World! " + i);
            System.out.printf("syncSend to topic %s sendResult=%s %n", demoTopic, sendResult);
        }
    }

}
