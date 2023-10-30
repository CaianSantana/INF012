package br.com.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.email.dtos.ConsultData;
import br.com.email.dtos.EmailDto;
import br.com.email.service.EmailService;

@Component
public class ConsultScheduledListener{

    EmailService service;

    @RabbitListener(queues = "medConsultAPI.v1.consult-scheduled.send-email")
    public void onConsultScheduled(ConsultData consultData){
        String mailText =
        "\nId da consulta: "+consultData.id()
        +"\nDoctor's name: "+consultData.doctorName()
        +"\nPatient's name: "+consultData.patientName();

        service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.doctorEmail(), "Consulta marcada!", mailText));
        service.sendEmail(new EmailDto("medConsultAPI@gmail", consultData.patientEmail(), "Consulta marcada!", mailText));

        System.out.println("Consulta recebida: "
        +"\nConsult ID: "+consultData.id()
        +"\nsending emails...");
    }
}