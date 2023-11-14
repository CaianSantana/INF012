package br.com.email.Listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import br.com.email.dtos.ConsultData;
import br.com.email.dtos.EmailDto;
import br.com.email.service.EmailService;

@Component
public class ConsultScheduledListener{

    @Autowired
    EmailService service;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "medConsultAPI.v1.consult-scheduled.send-email")
    public void onConsultScheduled(ConsultData consultData) throws MailException{
        

        System.out.println("Consult received."
        +"\nConsult ID: "+consultData.id()
        +"\nsending emails...");

        String mailText =
        "\nConsult Id: "+consultData.id()
        +"\nDoctor's name: "+consultData.doctorName()
        +"\nSpecialty: "+consultData.specialty()
        +"\nPatient's name: "+consultData.patientName();

        try {
            service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.doctorEmail().toString(), "Consult scheduled!", mailText));
            service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.patientEmail().toString(), "Consult scheduled!", mailText));
        } catch (MailException e) {
            System.err.println("Armazenando os emails inválidos da consulta de id: "+consultData.id());
            rabbitTemplate.convertAndSend("medConsultAPI.v1.consult-scheduled.dlx", null, consultData);
        }
    }
}