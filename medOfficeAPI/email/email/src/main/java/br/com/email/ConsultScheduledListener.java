package br.com.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = "medConsultAPI.v1.consult-scheduled.send-email")
    public void onConsultScheduled(ConsultData consultData) throws MailException{
        String mailText =
        "\nId da consulta: "+consultData.id()
        +"\nDoctor's name: "+consultData.doctorName()
        +"\nPatient's name: "+consultData.patientName();

        service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.doctorEmail().toString(), "Consult scheduled!", mailText));
        service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.patientEmail().toString(), "Consult scheduled!", mailText));

        System.out.println("Consult received: "
        +"\nConsult ID: "+consultData.id()
        +"\nsending emails...");
    }
}