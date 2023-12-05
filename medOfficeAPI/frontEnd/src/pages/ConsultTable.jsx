import React from 'react';
import { EntityTable } from '../components/EntityTable';
import { getConsults, deleteConsult } from '../services/consultApi';

const ConsultTable = () => {

    const columns = ['Médico', 'Paciente', 'Agendamento'];

    const handleDeleteConsult = async (consultId) => {
        try {
        await deleteConsult(consultId);
        console.log(`Médico removido com sucesso`);
        fetchData();
        } catch (error) {
        console.error('Erro ao remover médico', error);
        }
    };

    return (
    <EntityTable
      fetchData={getConsults}
      onDelete={handleDeleteConsult}
      columns={columns}
    />
  );
};

export default ConsultTable;