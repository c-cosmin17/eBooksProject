import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './manageTypes.css';

const ManageTypes = () => {
  const [types, setTypes] = useState([]);
  const [newTypeName, setNewTypeName] = useState('');
  const token = sessionStorage.getItem('authToken');
  
//extragem tipurile din baza de date
  useEffect(() => {
    fetchTypes();
  }, []);
  const fetchTypes = async () => {
    try {
      const response = await axios.get('http://localhost:9191/manager/types', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setTypes(response.data);
    } catch (error) {
      console.error('Error fetching types:', error);
    }
  };
  //stergem tipul typeid din baza de date
  const handleDeleteType = async (typeId) => {
    try {
      await axios.delete(`http://localhost:9191/manager/deleteType/${typeId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      fetchTypes();
    } catch (error) {
      console.error('Error deleting type:', error);
    }
  };
  //adaugam noul tip in baza de date
  const handleAddType = async () => {
    try {


        if (!newTypeName.trim()) {
            alert('Numele tipului nu poate fi gol!');
            return;
          }

      const newType = {
        id: null,
        name: newTypeName,
      };

      await axios.post('http://localhost:9191/manager/addType', newType, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      setNewTypeName('');
      fetchTypes();
    } catch (error) {
      console.error('Error adding type:', error);
    }
  };

  console.log(types);

  return (
    <div className="manage-types-container">
      {types.map((type) => (
        <div key={type.id} className="manage-types-item">
          <div className="manage-types-details">
            <p>Id: {type.id}</p>
            <p>Nume: {type.name}</p>
          </div>
          <button
            className="manage-types-button"
            onClick={() => handleDeleteType(type.id)}
          >
            Sterge
          </button>
        </div>
      ))}
      <input
        type="text"
        placeholder="Type Name"
        value={newTypeName}
        onChange={(e) => setNewTypeName(e.target.value)}
      />
      <button className="manage-types-button" onClick={handleAddType}>Adaugă</button>
    </div>
  );
};

export default ManageTypes;