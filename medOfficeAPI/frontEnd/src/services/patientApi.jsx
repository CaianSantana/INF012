import axios from './Api';

const baseUrl = '/patient-ms/patients';
const itemsPerPage = 10;

const getPatients = async (page) => {
  try {
    const response = await axios.get(`${baseUrl}/findAll/${page}`);
    console.log('Dados recebidos da API:', response.data);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter pacientes', error);
    throw error;
  }
};

const addPatient = async (newPatientData) => {
    try {
      const response = await axios.post(baseUrl, newPatientData);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar pacientes', error);
      throw error;
    }
  };
  
  const updatePatient = async (patientId, updatedPatientData) => {
    try {
      const response = await axios.put(`${baseUrl}/${patientId}`, updatedPatientData);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar pacientes', error);
      throw error;
    }
  };

  const deletePatient = async (patientId) => {
    try {
      const response = await axios.delete(`${baseUrl}/${patientId}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao remover patienta de ID ${patientId}`, error);
      throw error;
    }
  };
  
  export { getPatients, addPatient, updatePatient, deletePatient };