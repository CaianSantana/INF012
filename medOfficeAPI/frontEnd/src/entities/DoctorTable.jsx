import React, { useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getDoctors, deleteDoctor } from '../services/doctorApi';

const DoctorTable = () => {
    const [currentPage, setCurrentPage] = useState(0); // Página inicial

    const columns = ['nome', 'especialidade'];

    const handleDeleteDoctor = async (doctorId) => {
        try {
            await deleteDoctor(doctorId);
            console.log(`Médico removido com sucesso`);
            // Recarregar a tabela após excluir um médico
            fetchData();
        } catch (error) {
            console.error('Erro ao remover médico', error);
        }
    };

    const fetchData = async (page) => {
        try {
            const doctorsData = await getDoctors(page);
            return doctorsData;
        } catch (error) {
            console.error('Erro ao obter médicos', error);
            throw error;
        }
    };

    return (
        <EntityTable
            fetchData={() => fetchData(currentPage)}
            onDelete={handleDeleteDoctor}
            columns={columns}
        />
    );
};
export default DoctorTable;