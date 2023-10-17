package dtos;


import com.br.medConsultAPI.model.Date;


public record FormConsult(Long id, FormDoctor doctor, FormPatient patient, Date date) {

}
