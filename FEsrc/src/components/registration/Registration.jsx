import React, { useState } from 'react';
import './registration.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Registration = () => {
  const [formData, setFormData] = useState({
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
    selectedCardType: '',
    cardNumber: '',
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
//logica pentru butonul de submit
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.username || 
      !formData.firstName || 
      !formData.lastName || 
      !formData.email || 
      !formData.phone || 
      !formData.password || 
      !formData.selectedCardType || 
      !formData.cardNumber) {
      alert('Toate câmpurile sunt obligatorii.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:9191/register', formData);

      console.log('Registration successful:', response.data);
      navigate('/login');
    } catch (error) {
      console.error('Error registering user:', error.message);
    }
  };

  return (
    <div className="registration-container">
      <h2>Înregistrare</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="lastName">Nume:</label>
          <input type="text" id="lastName" name="lastName" value={formData.lastName} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="firstName">Prenume:</label>
          <input type="text" id="firstName" name="firstName" value={formData.firstName} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="phone">Telefon:</label>
          <input type="text" id="phone" name="phone" value={formData.phone} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="username">Nume utilizator:</label>
          <input type="text" id="username" name="username" value={formData.username} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="password">Parolă:</label>
          <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="selectedCardType">Alege tipul de card de credit:</label>
          <select
            id="selectedCardType"
            name="selectedCardType"
            value={formData.selectedCardType}
            onChange={handleChange}
          >
            <option value="">Selectează tipul de card</option>
            <option value="visa">Visa</option>
            <option value="mastercard">MasterCard</option>
            <option value="amex">American Express</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="cardNumber">Număr card:</label>
          <input type="text" id="cardNumber" name="cardNumber" value={formData.cardNumber} onChange={handleChange} />
        </div>
        <button className="form-group"type="submit">Înregistrează-te</button>
      </form>
    </div>
  );
};

export default Registration;
