package com.example.pizzaorder.controller;

import com.example.pizzaorder.messagingrabbitmq.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaOrderController {


    private final RabbitTemplate rabbitTemplate;

    public PizzaOrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping("/orders")
    String orderPizza(@RequestBody String pizzaOrder) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,
                "foo.bar.baz", pizzaOrder);

        return "Thanks for your order";
    }
}

