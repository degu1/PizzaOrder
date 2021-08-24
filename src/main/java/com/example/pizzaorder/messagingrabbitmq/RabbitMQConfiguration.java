package com.example.pizzaorder.messagingrabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //Tags the class as a source of bean definitions
public class RabbitMQConfiguration {
    public static final String topicExchangeName = "pizza-exchange";

    public static final String queueName = "pizza-orders";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

}
