package com.example.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * @program: rocketmq-multi-instance-demo
 * @description:
 * @author: shenqi
 * @create: 2022-08-24 14:22
 */
@Service
@RocketMQMessageListener(
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "tagb-consumer-group",
        selectorExpression = "${demo.rocketmq.tagB}"
)
public class TagBConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(String message) {
        System.out.printf("------- TagBConsumer received: %s \n", message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置当前实例的名称
        consumer.setInstanceName("tag-b-consumer-instance@" + UtilAll.getPid());
    }

}
