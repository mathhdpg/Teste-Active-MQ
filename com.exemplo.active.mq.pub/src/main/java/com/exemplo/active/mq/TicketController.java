package com.exemplo.active.mq;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping(value = "/buy")
    public void buyTicket(@RequestBody Ticket ticket) throws Exception {
        System.out.println("Recebido Ticket: " + ticket);
        System.out.println("Postando para ActiveMQ");


        Thread myRunnable =new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("Runnable running");
                jmsTemplate.convertAndSend("compra_queue",
                        new Ticket(ticket.getName(),
                                ticket.getPrice(),
                                ticket.getQuantity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        myRunnable.start();
    }
}
