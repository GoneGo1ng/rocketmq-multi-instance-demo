package com.example.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * @program: rocketmq-multi-instance-demo
 * @description:
 * @author: shenqi
 * @create: 2022-08-24 14:16
 */
@Service
@RocketMQMessageListener(
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "taga-consumer-group",
        selectorExpression = "${demo.rocketmq.tagA}"
)
public class TagAConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(String message) {
        System.out.printf("------- TagAConsumer received: %s \n", message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置当前实例的名称
        // consumer.setInstanceName("tag-a-consumer-instance@" + UtilAll.getPid());
        consumer.setInstanceName("tag-a-consumer-instance");
    }

}
