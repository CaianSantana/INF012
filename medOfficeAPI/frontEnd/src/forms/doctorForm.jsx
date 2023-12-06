import React, { useEffect, useState } from 'react';
import { addDoctor, updateDoctor } from '../services/doctorApi';
import AddressForm from '../components/AddressForm';
import EventBus from '../eventBus';

const DoctorForm = ({ selectedDoctor }) => {
  const [editing, setEditing] = useState(false);
  const [doctorData, setDoctorData] = useState({
    name: '',
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
    crm: '',
    specialty: ''
  });

  const resetData = () =>{
    setDoctorData({
      name: '',
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
    crm: '',
    specialty: ''
    });
  }

  const handleAddressChange = (updatedAddress) => {
    setDoctorData((prevData) => ({
      ...prevData,
      address: updatedAddress,
    }));
  };

  useEffect(() => {
    if (selectedDoctor) {
      setEditing(true);
      setDoctorData({
        name: selectedDoctor.name || '',
        email: selectedDoctor.email || '',
        phone: selectedDoctor.phone || '',
        address: {
          publicPlace: selectedDoctor.address?.publicPlace || '',
          number: selectedDoctor.address?.number || '',
          complement: selectedDoctor.address?.complement || '',
          neighborhood: selectedDoctor.address?.neighborhood || '',
          city: selectedDoctor.address?.city || '',
          state: selectedDoctor.address?.state || '',
          zipCode: selectedDoctor.address?.zipCode || '',
        },
        crm: selectedDoctor.crm || '',
        specialty: selectedDoctor.specialty || ''
      });
    } else {
      setEditing(false);
      resetData()
    }
  }, [selectedDoctor]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updatedData = {
        name: doctorData.name,
        // Se estiver editando, crm é null, senão pega o valor de doctorData.crm ou ''
        email: selectedDoctor ? null : doctorData.email || '',
        phone: doctorData.phone, 
        address: selectedDoctor ? null : doctorData.address || null, 
        crm: selectedDoctor ? null : doctorData.crm || '',
        specialty: selectedDoctor ? null : doctorData.specialty || ''
      };
      console.log(updatedData)
  
      if (selectedDoctor) {
        await updateDoctor(selectedDoctor.id, updatedData);
        console.log('Médico atualizado com sucesso');
        EventBus.publish("itemAdded")
      } else {
        const response = await addDoctor(updatedData);
        console.log('Médico adicionado com sucesso');
        EventBus.publish("itemAdded")
        console.log('Dados do paciente adicionados com sucesso:', response);
      }
      // Limpar os dados do formulário após a submissão
      resetData()
    } catch (error) {
        
      console.error('Erro durante a solicitação:', error);
    }
  };

  const handleCancelEdit = () => {
    setEditing(false);
    resetData()
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-section">
        <label htmlFor="name">Nome:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={doctorData.name}
          onChange={(e) => setDoctorData({ ...doctorData, name: e.target.value })}
          required
        />
      </div>

      <div className="form-section">
        <label htmlFor="crm">CRM:</label>
        <input
          type="text"
          id="crm"
          name="crm"
          value={doctorData.crm}
          onChange={(e) => setDoctorData({ ...doctorData, crm: e.target.value })}
          disabled={editing}
          required
        />
      </div>

      <div className="form-section">
        <label htmlFor="specialty">Especialidade:</label>
        <input
          type="text"
          id="specialty"
          name="specialty"
          value={doctorData.specialty}
          onChange={(e) => setDoctorData({ ...doctorData, specialty: e.target.value })}
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
          value={doctorData.email}
          onChange={(e) => setDoctorData({ ...doctorData, email: e.target.value })}
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
          value={doctorData.phone}
          onChange={(e) => setDoctorData({ ...doctorData, phone: e.target.value })}
        />
      </div>

      {/* Adicione o atributo disabled nos campos de endereço */}
      <div className="form-section address-form">
        <label>Endereço:</label>
        <AddressForm onChange={handleAddressChange} disabled={editing} />
      </div>

      <div className="button-container">
        <button type="submit">
          {editing ? 'Atualizar' : 'Adicionar'}
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

export default DoctorForm;
