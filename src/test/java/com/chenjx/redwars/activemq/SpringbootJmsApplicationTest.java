package com.chenjx.redwars.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * create by chenjx 2018/7/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTest {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        Destination activeMQQueue = new ActiveMQQueue("mytest.queue");

        for (int i = 0; i < 100; i++) {
            producer.sendMessage(activeMQQueue,i+"...myname is chenjx!!!");
        }
    }

}
