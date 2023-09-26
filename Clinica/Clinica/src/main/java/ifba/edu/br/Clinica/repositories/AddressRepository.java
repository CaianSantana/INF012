package ifba.edu.br.Clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.edu.br.Clinica.models.products.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
