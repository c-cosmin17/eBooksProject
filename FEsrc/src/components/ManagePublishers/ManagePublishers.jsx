// ManagePublishers.jsx

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './managePublishers.css';

const ManagePublishers = () => {
  const [publishers, setPublishers] = useState([]);
  const [newPublisherName, setNewPublisherName] = useState('');
  const token = sessionStorage.getItem('authToken');

  useEffect(() => {
    fetchPublishers();
  }, []);
  //extragem publicantii din baza de date
  const fetchPublishers = async () => {
    try {
      const response = await axios.get('http://localhost:9191/manager/publishers', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPublishers(response.data);
    } catch (error) {
      console.error('Error fetching publishers:', error);
    }
  };
  //stergem din baza de date
  const handleDeletePublisher = async (publisherId) => {
    try {
      await axios.delete(`http://localhost:9191/manager/deletePublisher/${publisherId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      fetchPublishers();
    } catch (error) {
      console.error('Error deleting publisher:', error);
    }
  };
  //adaugam in baza de date
  const handleAddPublisher = async () => {
    try {
        if (!newPublisherName.trim()) {
            alert('Numele publicantului nu poate fi gol!');
            return;
            }
      const newPublisher = {
        id: null,
        name: newPublisherName,
      };

      await axios.post('http://localhost:9191/manager/addPublisher', newPublisher, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      setNewPublisherName('');
      fetchPublishers();
    } catch (error) {
      console.error('Error adding publisher:', error);
    }
  };

  console.log(publishers);

  return (
    <div className="manage-publishers-container">
      {publishers.map((publisher) => (
        <div key={publisher.id} className="manage-publishers-item">
          <div className="manage-publishers-details">
            <p>Id: {publisher.id}</p>
            <p>Nume: {publisher.name}</p>
          </div>
          <button
            className="manage-publishers-button"
            onClick={() => handleDeletePublisher(publisher.id)}
          >
            Sterge
          </button>
        </div>
      ))}
      <input
        type="text"
        placeholder="Publisher Name"
        value={newPublisherName}
        onChange={(e) => setNewPublisherName(e.target.value)}
      />
      <button className="manage-publishers-button" onClick={handleAddPublisher}>Adaugă</button>
    </div>
  );
};

export default ManagePublishers;
