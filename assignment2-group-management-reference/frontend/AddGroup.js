import {useState} from "react";
import {useNavigate} from "react-router-dom";
import groupService from "./groupService";

export default function AddGroup(){
 const [groupName,setGroupName]=useState(""); const navigate=useNavigate();
 const saveGroup=()=>groupService.addGroup({groupName}).then(()=>{alert("Group Added Successfully");navigate("/");}).catch(()=>alert("Group Already Exists!!"));
 return <div className="container mt-5"><div className="card"><div className="card-header"><h3>Add New Group</h3></div><div className="card-body"><label>Group Name</label><input className="form-control" value={groupName} onChange={e=>setGroupName(e.target.value)}/><button className="btn btn-success mt-3" onClick={saveGroup}>Save Group</button><button className="btn btn-secondary mt-3 ms-2" onClick={()=>navigate("/")}>Back</button></div></div></div>;
}
