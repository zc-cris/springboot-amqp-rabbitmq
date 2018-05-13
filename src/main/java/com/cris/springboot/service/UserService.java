package com.cris.springboot.service;

import com.cris.springboot.bean.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author zc-cris
 * @Version 1.0
 **/
@Service
public class UserService {

    @RabbitListener(queues = {"cris.news"})
    public void receive(User user){
        System.out.println(user);
    }

    @RabbitListener(queues = {"cris"})
    public void receive2(Message message){
        System.out.println(message.getBody()+"---------------------消息体"); //消息体
        System.out.println(message.getMessageProperties()+"----------消息头"); //消息头
    }




}
