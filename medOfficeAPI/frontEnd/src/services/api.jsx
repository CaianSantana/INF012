// services/api.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8082', // Altere conforme necessário
});

export default instance;