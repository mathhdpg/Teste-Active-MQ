package com.exemplo.active.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;


@Component
public class EventListener {

    @JmsListener(destination = "retorno_queue", containerFactory = "defaultFactory")
    public void receiveMessage(String mensagem) {
        System.out.println("Retorno:" + mensagem);
    }

}