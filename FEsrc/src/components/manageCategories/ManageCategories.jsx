import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './manageCategories.css';

const ManageCategories = () => {
  const [categories, setCategories] = useState([]);
  const [newCategoryName, setNewCategoryName] = useState('');
  const token = sessionStorage.getItem('authToken');
  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await axios.get('http://localhost:9191/manager/categories', {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
      });
      setCategories(response.data);
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };

  const handleDeleteCategory = async (categoryId) => {
    try {
      await axios.delete(`http://localhost:9191/manager/deleteCategory/${categoryId}`, {
        headers: {
          Authorization: `Bearer ${token}`, 
        },
      });
      fetchCategories();
    } catch (error) {
      console.error('Error deleting category:', error);
    }
  };



const handleAddCategory = async () => {
    try {
      // Verificare dacă numele categoriei nu este gol
      if (!newCategoryName.trim()) {
        alert('Numele categoriei nu poate fi gol!');
        return;
      }
  
      const newCategory = {
        id: null,
        name: newCategoryName.trim(), // Se elimină spațiile albe la început și sfârșit
      };
  
      await axios.post('http://localhost:9191/manager/addCategory', newCategory, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
  
      setNewCategoryName('');
      fetchCategories();
    } catch (error) {
      console.error('Error adding category:', error);
    }
  };
  
  

  console.log(categories);

  return (
    <div className="manage-categories-container">
      {categories.map((category) => (
        <div key={category.id} className="manage-categories-item">
          <div className="manage-categories-details">
            <p>Id: {category.id}</p>
            <p>Nume: {category.name}</p>
          </div>
          <button
            className="manage-categories-button"
            onClick={() => handleDeleteCategory(category.id)}
          >
            Sterge
          </button>
        </div>
      ))}
      <input
        type="text"
        placeholder="Category Name"
        value={newCategoryName}
        onChange={(e) => setNewCategoryName(e.target.value)}
      />
      <button className="manage-categories-button" onClick={handleAddCategory}>Adaugă</button>
    </div>
  );
};

export default ManageCategories;