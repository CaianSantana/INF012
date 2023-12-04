import React from 'react';
import { EntityTable } from '../components/EntityTable';
import { getDoctors, deleteDoctor } from '../services/doctorApi';

const DoctorTable = () => {

    const columns = ['nome', 'especialidade'];

    const handleDeleteDoctor = async (doctorId) => {
        try {
        await deleteDoctor(doctorId);
        console.log(`Médico removido com sucesso`);
        } catch (error) {
        console.error('Erro ao remover médico', error);
        }
    };

    return (
    <EntityTable
      fetchData={getDoctors}
      onDelete={handleDeleteDoctor}
      columns={columns}
    />
  );
};

export default DoctorTable;