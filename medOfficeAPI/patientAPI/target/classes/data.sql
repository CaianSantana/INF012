INSERT INTO Addresses(public_Place, number, complement, neighborhood, city, state, zip_Code) 
VALUES('Rua 7 de Janeiro', 54, NULL, 'Cidade Nova','Salvador','Bahia', '40313-170');
INSERT INTO Addresses(public_Place, number, complement, neighborhood, city, state, zip_Code) 
VALUES('Rua 1 de Abril', 54, NULL, 'Cidade Velha','Matador','Bahia', '170-40313');


INSERT INTO PATIENTS(name, cpf, email, phone, address_id, status) VALUES('Caian','06600870575', 'paciente@email.com', '(71)98696-7582',2, 'ACTIVE');