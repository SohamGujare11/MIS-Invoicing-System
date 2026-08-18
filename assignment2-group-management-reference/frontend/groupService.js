import axios from "axios";

const API_URL = "https://group-management-system-production.up.railway.app/groups";
const getGroups=()=>axios.get(API_URL);
const addGroup=(group)=>axios.post(API_URL,group);
const updateGroup=(id,group)=>axios.put(`${API_URL}/${id}`,group);
const deleteGroup=(id)=>axios.delete(`${API_URL}/${id}`);
export default {getGroups,addGroup,updateGroup,deleteGroup};
