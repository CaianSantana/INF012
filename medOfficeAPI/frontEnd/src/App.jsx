import React from 'react';
import {BrowserRouter, Routes ,Route } from 'react-router-dom';
import DoctorTable from './pages/DoctorTable';
import PatientTable from './pages/PatientTable';
import ConsultTable from './pages/ConsultTable';
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
