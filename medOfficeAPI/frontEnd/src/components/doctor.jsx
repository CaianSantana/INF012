import React, { useState, useEffect } from 'react';
import axios from '../services/Api';

const Doctor = () => {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    const fetchdoctors = async () => {
      try {
        const response = await axios.get('/doctors');
        setDoctors(response.data);
      } catch (error) {
        console.error('Erro ao obter dados dos médicos', error);
      }
    };

    fetchdoctors();
  }, []);

  return (
    <div>
      <h2>Doctor List</h2>
      <ul>
        {doctors.map((doctor) => (
          <li key={doctor.id}>{doctor.nome}</li>
        ))}
      </ul>
    </div>
  );
};

export default Doctor;