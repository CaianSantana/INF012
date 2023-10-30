package br.com.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.email.dtos.ConsultData;

@Component
public class ConsultScheduledListener{

    @RabbitListener(queues = "medConsultAPI.v1.consult-scheduled.send-email")
    public void onConsultScheduled(ConsultData consultData){
        System.out.println("Consulta recebida: "
        +"\nId da consulta: "+consultData.id()
        +"\nNome do médico: "+consultData.doctorName()
        +"\nEmail do médico: "+consultData.doctorEmail()
        +"\nNome do paciente: "+consultData.patientName()
        +"\nEmail do paciente: "+consultData.patientEmail());
    }

}