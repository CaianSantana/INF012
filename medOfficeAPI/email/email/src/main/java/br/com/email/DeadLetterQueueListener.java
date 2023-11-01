package br.com.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import br.com.email.dtos.ConsultData;

@Component
public class DeadLetterQueueListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String X_RETRY_HEADER = "x-dlq-retry";

    private static final String DLQ = "medConsultAPI.v1.consult-scheduled.dlx.send-email.dlq";

    @RabbitListener(queues = DLQ)
    public void processor(ConsultData dto, @Headers Map<String, Object> headers){
        Integer retryHeader = (Integer) headers.get(X_RETRY_HEADER);

        if(retryHeader == null){
            retryHeader = 0;
        }

        System.out.println("Reprocessando o envio dos emails da consulta de id: "+dto.id());

        if(retryHeader<3){
            Map<String, Object> updatedHeaders = new HashMap<>(headers);

            int tryCount = retryHeader+1;
            updatedHeaders.put(X_RETRY_HEADER, tryCount);

            //Reprocessamento

            final MessagePostProcessor messagePostProcessor = message ->{
                MessageProperties messageProperties = message.getMessageProperties();
                updatedHeaders.forEach(messageProperties::setHeader);
                return message;
            };

            System.out.println("Reenviando os emails da consulta de id: "+dto.id());
            this.rabbitTemplate.convertAndSend(DLQ, null, dto, messagePostProcessor);
        }
    }
}