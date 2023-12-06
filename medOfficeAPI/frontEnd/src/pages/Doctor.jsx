import React, { useEffect, useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getDoctors, deleteDoctor } from '../services/doctorApi';
import DoctorForm from '../forms/doctorForm';
import { useNavigate } from 'react-router-dom';

const Doctor = () => {
    const [currentPage, setCurrentPage] = useState(0);
    const [selectedDoctor, setSelectedDoctor] = useState(null);
    const navigate = useNavigate();

    const columns = ['name', 'specialty'];

    const handleVoltar = () => {
      // Navegar de volta para a tela inicial
      navigate('/');
    };

    const handleDeleteDoctor = async (doctorId) => {
        try {
            await deleteDoctor(doctorId);
            console.log(`médico removido com sucesso`);
            fetchData(currentPage);
        } catch (error) {
            console.error('Erro ao remover médico', error);
        }
    };

    const handleEditDoctor = (doctor) => {
        setSelectedDoctor(doctor);
      };
    

      const handleCancelEdit = () => {
        setSelectedDoctor(null);
      };
    
      const fetchData = async (page = currentPage) => {  // Adicionado valor padrão para page
        try {
          const doctorsData = await getDoctors(page);
          console.log('Dados recebidos na DoctorTable:', doctorsData);
          return doctorsData;
        } catch (error) {
          console.error('Erro ao obter médicos', error);
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
              onDelete={handleDeleteDoctor}
              onEdit={handleEditDoctor} // Passando a função onEdit para a EntityTable
              columns={columns}
            />
          </div>
          <div className="form">
            <DoctorForm
              selectedDoctor={selectedDoctor}
            />
          </div>
        </section>
      );
    };
    
    export default Doctor;