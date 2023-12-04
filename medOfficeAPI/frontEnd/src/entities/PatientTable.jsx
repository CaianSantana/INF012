import React, { useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getPatients, deletePatient } from '../services/patientApi';

const PatientTable = () => {
    const [currentPage, setCurrentPage] = useState(0);

    const columns = ['name', 'email'];

    const handleDeletePatient = async (patientId) => {
        try {
            await deletePatient(patientId);
            console.log(`paciente removido com sucesso`);
            fetchData();
        } catch (error) {
            console.error('Erro ao remover paciente', error);
        }
    };

    const fetchData = async (page) => {
        try {
            const patientsData = await getPatients(page);
            console.log('Dados recebidos na PatientTable:', patientsData);
            return patientsData;
        } catch (error) {
            console.error('Erro ao obter paciente', error);
            throw error;
        }
    };

    return (
        <div className="container">
            <EntityTable
                fetchData={() => fetchData(currentPage)}
                onDelete={handleDeletePatient}
                columns={columns}
            />
        </div>
    );
};
export default PatientTable;