package com.cris.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @Author zc-cris
* @Description 自动配置：
*   1. RabbitAutoConfiguration
*   2. 自动配置了连接工厂ConnectionFactory
*   3. RabbitProperties：封装了RabbitMQ的配置信息
*   4. RabbitTemplate：给RabbitMQ发送和接收消息的模板
*   5. AmqpAdmin：RabbitMQ的系统管理功能组件
*   6. @EnableRabbit + @RabbitListener 开启监听消息队列的消息，如果消息发送到exchange然后被放入queue，就会被
*       对应的监听方法获取到消息
* @Param 
* @return 
**/
@EnableRabbit
@SpringBootApplication
public class SpringbootAmqpRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpRabbitmqApplication.class, args);
    }
}
