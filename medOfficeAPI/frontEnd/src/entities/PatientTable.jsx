import React, { useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getPatients, deletePatient } from '../services/patientApi';

const PatientTable = () => {
    const [currentPage, setCurrentPage] = useState(0); // Página inicial

    const columns = ['nome', 'especialidade'];

    const handleDeletePatient = async (patientId) => {
        try {
            await deletePatient(patientId);
            console.log(`Médico removido com sucesso`);
            // Recarregar a tabela após excluir um médico
            fetchData();
        } catch (error) {
            console.error('Erro ao remover paciente', error);
        }
    };

    const fetchData = async (page) => {
        try {
            const patientsData = await getPatients(page);
            return patientsData;
        } catch (error) {
            console.error('Erro ao obter paciente', error);
            throw error;
        }
    };

    return (
        <EntityTable
            fetchData={() => fetchData(currentPage)}
            onDelete={handleDeletePatient}
            columns={columns}
        />
    );
};
export default PatientTable;