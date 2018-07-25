package com.chenjx.redwars.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * create by chenjx 2018/7/17
 */
@Component
public class Consumer2 {
    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer2接收到的报文为:" + text);
        return "return message" + text;
    }
}
