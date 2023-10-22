package dtos;

import com.br.medConsultAPI.enums.Status;

public record  FormPatient (Long id, String name, String cpf, String email, String phone, Long IDaddress, Status status)  {

}
