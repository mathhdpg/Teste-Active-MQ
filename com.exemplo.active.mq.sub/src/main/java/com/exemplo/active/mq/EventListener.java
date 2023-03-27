package com.exemplo.active.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;

@Component
public class EventListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "compra_queue", containerFactory = "defaultFactory")
    public void receiveMessage(Ticket ticket) {
        // throw new RuntimeException("Erro ao salvar " + ticket);
        System.out.println("Mensagem da fila:" + ticket);

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        jmsTemplate.convertAndSend("retorno_queue", "Ticket: " + ticket.getName() + " persistido com sucesso");
        
    }

}