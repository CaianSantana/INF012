import React, { useEffect, useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getPatients, deletePatient } from '../services/patientApi';
import PatientForm from '../forms/patientForm';
import EventBus from '../eventBus';

const PatientTable = () => {
    const [currentPage, setCurrentPage] = useState(0);
    const [selectedPatient, setSelectedPatient] = useState(null);
  
    const columns = ['name', 'email', 'actions'];

    const handleDeletePatient = async (patientId) => {
        try {
            await deletePatient(patientId);
            console.log(`paciente removido com sucesso`);
            fetchData(currentPage);
        } catch (error) {
            console.error('Erro ao remover paciente', error);
        }
    };

    const handleEditPatient = (patient) => {
        setSelectedPatient(patient);
      };
    

      const handleCancelEdit = () => {
        setSelectedPatient(null);
      };
    
      const handleAddPatient = async (patientData) => {
        try {
          if (selectedPatient) {
            await updatePatient(selectedPatient.id, patientData);
            console.log('Paciente atualizado com sucesso');
            handleCancelEdit();
          } else {
            await addPatient(patientData);
            console.log('Paciente adicionado com sucesso');
          }
    
          fetchData(currentPage);
        } catch (error) {
          console.error('Erro ao adicionar/atualizar paciente', error);
        }
      };
    
      const fetchData = async (page = currentPage) => {  // Adicionado valor padrão para page
        try {
          const patientsData = await getPatients(page);
          console.log('Dados recebidos na PatientTable:', patientsData);
          return patientsData;
        } catch (error) {
          console.error('Erro ao obter pacientes', error);
          throw error;
        }
      };
    
      useEffect(() => {
        fetchData();
      }, [currentPage]);

      return (
        <section>
          <button onClick={() => fetchData(currentPage)}>Atualizar Dados</button>
          <div className="container">
            <EntityTable
              fetchData={() => fetchData(currentPage)}
              onDelete={handleDeletePatient}
              onEdit={handleEditPatient} // Passando a função onEdit para a EntityTable
              columns={columns}
            />
          </div>
          <div className="form">
            <PatientForm
              onSubmit={handleAddPatient}
              selectedPatient={selectedPatient}
            />
          </div>
        </section>
      );
    };
    
    export default PatientTable;