package com.cris.springboot;

import com.cris.springboot.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    // 通过编码的方式创建exchange，queue，binding
    @Test
    public void test2(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue", true));
//        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE, "amqpAdmin.exchange", "cris521", null));
    amqpAdmin.deleteQueue("amqpAdmin.queue");
    amqpAdmin.deleteExchange("amqpAdmin.exchange");

    }

    /**
    * @Author zc-cris
    * @Description 使用springboost 整合rabbitmq的方式来操作rabbitmq消息中间件
     * 1. 点对点(单播)
    * @Param []
    * @return void
    **/
    @Test
    public void contextLoads() {
//        可以自定义message的消息体和消息头
//        rabbitTemplate.send(exchange, routeKey,message);


//        object默认作为消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq 服务器
//        rabbitTemplate.convertAndSend(exchange, routeKey, Object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这个第一个通过springboot 发送过来的消息");
        map.put("data", Arrays.asList("123", 321, true));
//        rabbitTemplate.convertAndSend("exchange.direct", "cris.news", map);
        // 测试javaBean对象能否以json格式发送到服务器以及从服务器接收（ok）
        rabbitTemplate.convertAndSend("exchange.direct", "cris", new User("cris", 21));

    }
    // 取出rabbitmq 服务器消息队列里面的信息并且转换为java对象(消息队列里的消息：先进先出)
    @Test
    public void testReceive(){
        Object o = rabbitTemplate.receiveAndConvert("cris");
        System.out.println(o.getClass());   //class java.util.HashMap
        System.out.println(o);  // {msg=这个第一个通过springboot 发送过来的消息, data=[123, 321, true]}
    }

    // 测试广播模式的交换器
    @Test
    public void test(){
        rabbitTemplate.convertAndSend("exchange.fanout", "", new User("重庆吴亦凡", 25));
    }



}
