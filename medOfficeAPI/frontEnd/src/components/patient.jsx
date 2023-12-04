import React, { useState, useEffect } from 'react';
import axios from '../services/Api';

const Patient = () => {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    const fetchpatients = async () => {
      try {
        const response = await axios.get('/patients');
        setPatients(response.data);
      } catch (error) {
        console.error('Erro ao obter dados dos pacientes', error);
      }
    };

    fetchpatients();
  }, []);

  return (
    <div>
      <h2>Patient List</h2>
      <ul>
        {patients.map((patient) => (
          <li key={patient.id}>{patient.nome}</li>
        ))}
      </ul>
    </div>
  );
};

export default Patient;