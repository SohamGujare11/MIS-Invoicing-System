import axios from "axios";

const API_URL = "http://localhost:8080/estimates";

const getEstimates = () => axios.get(API_URL);
const getEstimateById = (id) => axios.get(`${API_URL}/${id}`);
const addEstimate = (estimate) => axios.post(API_URL, estimate);
const updateEstimate = (id, estimate) => axios.put(`${API_URL}/${id}`, estimate);
const deleteEstimate = (id) => axios.delete(`${API_URL}/${id}`);
const getEstimatesByChain = (chainId) => axios.get(`${API_URL}/chain/${chainId}`);
const getEstimatesByGroup = (groupName) => axios.get(`${API_URL}/group/${encodeURIComponent(groupName)}`);
const getEstimatesByBrand = (brandName) => axios.get(`${API_URL}/brand/${encodeURIComponent(brandName)}`);
const getEstimatesByZone = (zoneName) => axios.get(`${API_URL}/zone/${encodeURIComponent(zoneName)}`);

export default {
  getEstimates,
  getEstimateById,
  addEstimate,
  updateEstimate,
  deleteEstimate,
  getEstimatesByChain,
  getEstimatesByGroup,
  getEstimatesByBrand,
  getEstimatesByZone
};
