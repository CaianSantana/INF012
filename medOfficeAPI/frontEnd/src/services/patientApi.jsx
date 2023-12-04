import axios from './Api';

const baseUrl = '/patient-ms/patients';

const getPatients = async () => {
  try {
    const response = await axios.get(baseUrl);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter médicos', error);
    throw error;
  }
};

const addPatient = async (newPatientData) => {
    try {
      const response = await axios.post(baseUrl, newPatientData);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar médico', error);
      throw error;
    }
  };
  
  const updatePatient = async (patientId, updatedPatientData) => {
    try {
      const response = await axios.put(`${baseUrl}/${patientId}`, updatedPatientData);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar médico', error);
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