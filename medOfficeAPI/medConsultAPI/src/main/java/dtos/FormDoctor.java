package dtos;

import enums.Specialty;
import enums.Status;

public record  FormDoctor (Long id, String name, String cpf, String email, String phone, Long IDaddress, String crm, Specialty specialty, Status status)  {

}
