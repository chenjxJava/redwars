package com.chenjx.redwars.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * create by chenjx 2018/7/17
 */
//@Component
public class Consumer {
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer接收到的报文为:" + text);
    }
}
