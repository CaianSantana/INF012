import React from 'react';
import {BrowserRouter, Routes ,Route } from 'react-router-dom';
import Doctor from './pages/Doctor';
import Patient from './pages/Patient';
import Consult from './pages/Consult';
import Home from './components/Home';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/doctor' element={<Doctor/>} />
        <Route path='/consult' element={<Consult/>} />
        <Route path='/patient' element={<Patient/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
