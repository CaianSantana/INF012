// services/doctorApi.js
import axios from './Api';

const baseUrl = '/doctor-ms/doctors';
const itemsPerPage = 10;

const getDoctors = async (page) => {
  try {
    const response = await axios.get(`${baseUrl}/${page}`);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter médicos', error);
    throw error;
  }
};


const addDoctor = async (newDoctorData) => {
    try {
      const response = await axios.post(baseUrl, newDoctorData);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar médico', error);
      throw error;
    }
  };
  
  const updateDoctor = async (doctorId, updatedDoctorData) => {
    try {
      const response = await axios.put(`${baseUrl}/${doctorId}`, updatedDoctorData);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar médico', error);
      throw error;
    }
  };

  const deleteDoctor = async (doctorId) => {
    try {
      const response = await axios.delete(`${baseUrl}/${doctorId}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao remover médico de ID ${doctorId}`, error);
      throw error;
    }
  };
  
  export { getDoctors, addDoctor, updateDoctor, deleteDoctor };