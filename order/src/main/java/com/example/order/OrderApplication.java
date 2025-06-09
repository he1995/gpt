package com.example.order;

import com.example.common.message.PaySuccessMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;
import java.util.logging.Logger;

@SpringBootApplication
public class OrderApplication {

    private static final Logger log = Logger.getLogger(OrderApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public Consumer<Message<PaySuccessMessage>> consumer() {
        return msg -> {
            log.info(Thread.currentThread().getName() + " Consumer1 Receive New Messages: " + msg.getPayload().getOrderId());

        };
    }

}
