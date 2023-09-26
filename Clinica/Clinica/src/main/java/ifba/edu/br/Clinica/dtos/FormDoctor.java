package ifba.edu.br.Clinica.dtos;

import ifba.edu.br.Clinica.models.enums.Occupation;
import ifba.edu.br.Clinica.models.enums.Specialty;
import ifba.edu.br.Clinica.models.products.Address;

public record  FormDoctor(Long id, Occupation occupation, String name, String cpf, String email, Address address, String crm, Specialty specialty) {


}
