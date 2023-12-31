import axios from './Api';

const baseUrl = '/consult-ms/Consults';

const getConsults = async () => {
  try {
    const response = await axios.get(baseUrl);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter consultas', error);
    throw error;
  }
};

const addConsult = async (newConsultData) => {
    try {
      const response = await axios.post(baseUrl, newConsultData);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar consulta', error);
      throw error;
    }
  };
  

  const deleteConsult = async (consultId, cancelReason) => {
    try {
      const response = await axios.delete(`${baseUrl}/cancel/${consultId}`, {
        data: { cancelReason }, // Envie os dados no corpo da solicitação
      });
      return response.data;
    } catch (error) {
      console.error(`Erro ao remover consulta de ID ${consultId}`, error);
      throw error;
    }
  };
  
  export { getConsults, addConsult, deleteConsult };