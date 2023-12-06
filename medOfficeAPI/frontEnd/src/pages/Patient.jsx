import React, { useEffect, useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getPatients, deletePatient } from '../services/patientApi';
import PatientForm from '../forms/patientForm';
import { useNavigate } from 'react-router-dom';

const Patient = () => {
    const [currentPage, setCurrentPage] = useState(0);
    const [selectedPatient, setSelectedPatient] = useState(null);
    const navigate = useNavigate();
  
    const columns = ['name', 'email'];

    const handleVoltar = () => {
      // Navegar de volta para a tela inicial
      navigate('/');
    };

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
          <button onClick={() => handleVoltar()}>Voltar</button>
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
              selectedPatient={selectedPatient}
            />
          </div>
        </section>
      );
    };
    
    export default Patient;