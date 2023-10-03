package ifba.edu.br.Clinica.dtos;

import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.models.Address;

public record FormPatient(Long id, Occupation occupation, String name, String cpf, String email, Address address) {

}
