import React, { useState, useEffect } from 'react';
import axios from '../services/Api';

const Consult = () => {
  const [consults, setConsults] = useState([]);

  useEffect(() => {
    const fetchconsults = async () => {
      try {
        const response = await axios.get('/consults');
        setConsults(response.data);
      } catch (error) {
        console.error('Erro ao obter dados das consultas', error);
      }
    };

    fetchconsults();
  }, []);

  return (
    <div>
      <h2>Consult List</h2>
      <ul>
        {consults.map((consult) => (
          <li key={consult.id}>{consult.nome}</li>
        ))}
      </ul>
    </div>
  );
};

export default Consult;