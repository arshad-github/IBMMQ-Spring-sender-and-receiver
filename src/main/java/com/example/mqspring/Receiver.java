package com.example.mqspring;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {

    @JmsListener(destination = IbmmqSpring.QUEUE, containerFactory = "ConnectionFactory")
    public void onMessageReceived(String message) throws IOException {
        try {
            System.out.println("Received from " + IbmmqSpring.QUEUE + ":\n" + message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
