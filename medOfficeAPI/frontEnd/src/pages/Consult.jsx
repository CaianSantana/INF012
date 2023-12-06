import React, { useEffect, useState } from 'react';
import { EntityTable } from '../components/EntityTable';
import { getConsults, deleteConsult } from '../services/consultApi';
import ConsultForm from '../forms/consultForm';
import EventBus from '../eventBus';
import { useNavigate } from 'react-router-dom';

const Consult = () => {
  const [selectedConsult, setSelectedConsult] = useState(null);
  const navigate = useNavigate();

  const columns = ['doctorName', 'patientName', 'schedule'];

  const handleVoltar = () => {
    navigate('/');
  };

  const handleEditConsult = (consult) => {
    setSelectedConsult(consult);
  };

  const handleAddConsult = async () => {
  };

  const handleCancelSelectConsult = () => {
    setSelectedConsult(null);
  };


  const handleDeleteConsult = (consult) => {
  };



  const fetchData = async () => {
    try {
      const consultsData = await getConsults();
      console.log('Dados recebidos na ConsultTable:', consultsData);
      return consultsData;
    } catch (error) {
      console.error('Erro ao obter consultas', error);
      throw error;
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

    return (
    <section>
      <button onClick={handleVoltar}>Voltar</button>
      <div className="container">
        <EntityTable
          fetchData={() => fetchData()}
          onDelete={handleDeleteConsult}
          onEdit={handleEditConsult}
          columns={columns}
        />
      </div>
      <div className="form">
        <ConsultForm
          onCancelSelect={handleCancelSelectConsult} // Adiciona a função de cancelar seleção
          selectedConsult={selectedConsult}
        />
      </div>
    </section>
  );
};

export default Consult;