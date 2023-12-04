import React from 'react';
import {BrowserRouter, Routes ,Route } from 'react-router-dom';
import DoctorTable from './entities/DoctorTable';
import PatientTable from './entities/PatientTable';
import ConsultTable from './entities/ConsultTable';
import Home from './components/Home';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/doctor' element={<DoctorTable/>} />
        <Route path='/consult' element={<ConsultTable/>} />
        <Route path='/patient' element={<PatientTable/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
