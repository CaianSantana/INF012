import React from 'react';
import { EntityTable } from '../components/EntityTable';
import { getPatients, deletePatient } from '../services/patientApi';

const PatientTable = () => {

    const columns = ['nome', 'email'];

    const handleDeletePatient = async (patientId) => {
        try {
        await deletePatient(patientId);
        console.log(`Médico removido com sucesso`);
        } catch (error) {
        console.error('Erro ao remover médico', error);
        }
    };

    return (
    <EntityTable
      fetchData={getPatients}
      onDelete={handleDeletePatient}
      columns={columns}
    />
  );
};

export default PatientTable;