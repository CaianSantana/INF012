import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div>
      <h2>Bem-vindo à Página Inicial!</h2>
      <p>Escolha uma seção para navegar:</p>
      <ul>
        <li>
          <Link to="/doctor">Médicos</Link>
        </li>
        <li>
          <Link to="/consult">Consultas</Link>
        </li>
        <li>
          <Link to="/patient">Pacientes</Link>
        </li>
      </ul>
    </div>
  );
};

export default Home;