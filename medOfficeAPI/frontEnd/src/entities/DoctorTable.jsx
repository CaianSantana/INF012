import React, { useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getDoctors, deleteDoctor } from '../services/doctorApi';

const DoctorTable = () => {
    const [currentPage, setCurrentPage] = useState(0); // Página inicial

    const columns = ['name', 'specialty'];

    const handleDeleteDoctor = async (doctorId) => {
        try {
            await deleteDoctor(doctorId);
            console.log(`Médico removido com sucesso`);
            fetchData();
        } catch (error) {
            console.error('Erro ao remover médico', error);
        }
    };

    const fetchData = async (page) => {
        try {
            const doctorsData = await getDoctors(page);
            console.log('Dados recebidos na DoctorTable:', doctorsData);
            return doctorsData;
        } catch (error) {
            console.error('Erro ao obter médicos', error);
            throw error;
        }
    };

    return (
        <div className="container">
            <EntityTable
                fetchData={() => fetchData(currentPage)}
                onDelete={handleDeleteDoctor}
                columns={columns}
            />
        </div>
     );        
};
export default DoctorTable;