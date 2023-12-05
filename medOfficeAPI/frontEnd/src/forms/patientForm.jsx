import React, { useEffect, useState } from 'react';
import { addPatient, updatePatient } from '../services/patientApi';
import AddressForm from '../components/AddressForm';
import EventBus from '../eventBus';

const PatientForm = ({ onSubmit, selectedPatient }) => {
  const [editing, setEditing] = useState(false);
  const [patientData, setPatientData] = useState({
    name: '',
    cpf: '',
    email: '',
    phone: '',
    address: {
      publicPlace: '',
      number: '',
      complement: '',
      neighborhood: '',
      city: '',
      state: '',
      zipCode: '',
    },
  });

  const handleAddressChange = (updatedAddress) => {
    setPatientData((prevData) => ({
      ...prevData,
      address: updatedAddress,
    }));
  };

  useEffect(() => {
    if (selectedPatient) {
      setEditing(true);
      setPatientData({
        name: selectedPatient.name || '',
        cpf: selectedPatient.cpf || '',
        email: selectedPatient.email || '',
        phone: selectedPatient.phone || '',
        address: {
          publicPlace: selectedPatient.address?.publicPlace || '',
          number: selectedPatient.address?.number || '',
          complement: selectedPatient.address?.complement || '',
          neighborhood: selectedPatient.address?.neighborhood || '',
          city: selectedPatient.address?.city || '',
          state: selectedPatient.address?.state || '',
          zipCode: selectedPatient.address?.zipCode || '',
        },
      });
    } else {
      setEditing(false);
      setPatientData({
        name: '',
        cpf: '',
        email: '',
        phone: '',
        address: {
          publicPlace: '',
          number: '',
          complement: '',
          neighborhood: '',
          city: '',
          state: '',
          zipCode: '',
        },
      });
    }
  }, [selectedPatient]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updatedData = {
        name: patientData.name,
        cpf: selectedPatient ? null : patientData.cpf || '', // Se estiver editando, cpf é null, senão pega o valor de patientData.cpf ou ''
        email: selectedPatient ? null : patientData.email || '', // Mesma lógica aqui
        phone: selectedPatient ? null : patientData.phone || '', // E aqui
        address: selectedPatient ? null : patientData.address || null, // E aqui, se estiver editando, address é null, senão pega o valor de patientData.address ou null
      };
  
      if (selectedPatient) {
        await updatePatient(selectedPatient.id, updatedData);
        console.log('Paciente atualizado com sucesso');
        EventBus.publish("patientAdded")
      } else {
        const response = await addPatient(updatedData);
        console.log('Paciente adicionado com sucesso');
        EventBus.publish("patientAdded")
        console.log('Dados do paciente adicionados com sucesso:', response);
      }
      // Limpar os dados do formulário após a submissão
      setPatientData({
        name: '',
        cpf: '',
        email: '',
        phone: '',
        address: {
          publicPlace: '',
          number: '',
          complement: '',
          neighborhood: '',
          city: '',
          state: '',
          zipCode: '',
        },
      });
    } catch (error) {
      console.error('Erro durante a solicitação:', error);
    }
  };

  const handleCancelEdit = () => {
    setEditing(false);
    setPatientData({
      name: '',
      cpf: '',
      email: '',
      phone: '',
      address: {
        publicPlace: '',
        number: '',
        complement: '',
        neighborhood: '',
        city: '',
        state: '',
        zipCode: '',
      },
    });
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-section">
        <label htmlFor="name">Nome:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={patientData.name}
          onChange={(e) => setPatientData({ ...patientData, name: e.target.value })}
          required
        />
      </div>

      <div className="form-section">
        <label htmlFor="cpf">CPF:</label>
        <input
          type="text"
          id="cpf"
          name="cpf"
          value={patientData.cpf}
          onChange={(e) => setPatientData({ ...patientData, cpf: e.target.value })}
          disabled={editing}
          required
        />
      </div>

      <div className="form-section">
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={patientData.email}
          onChange={(e) => setPatientData({ ...patientData, email: e.target.value })}
          disabled={editing}
          required
        />
      </div>

      <div className="form-section">
        <label htmlFor="phone">Telefone:</label>
        <input
          type="text"
          id="phone"
          name="phone"
          value={patientData.phone}
          onChange={(e) => setPatientData({ ...patientData, phone: e.target.value })}
          disabled={editing}
        />
      </div>

      {/* Adicione o atributo disabled nos campos de endereço */}
      <div className="form-section address-form">
        <label>Endereço:</label>
        <AddressForm onChange={handleAddressChange} disabled={editing} />
      </div>

      <div className="button-container">
        <button type="submit">
          {editing ? 'Atualizar Paciente' : 'Adicionar Paciente'}
        </button>
        {editing && (
          <button type="button" onClick={handleCancelEdit}>
            Cancelar Edição
          </button>
        )}
      </div>
    </form>
  );
};

export default PatientForm;
