// Authentication.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Importăm useNavigate în loc de useHistory
import './authentication.css';

const Authentication = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate(); // Folosim useNavigate în loc de useHistory

  

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:9191/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        setIsLoggedIn(true);
        console.log('Authentication successful:', data);
        alert('Authentication successful!');
        const token = data.accessToken;
        const role = data.role;
        const id = data.id;
        console.log('Authentication successful:', token,role, id);
        sessionStorage.setItem('authToken', token);
        sessionStorage.setItem('role', role);
        sessionStorage.setItem('id', id);
        navigate('/');
      } else {
        alert('Authentication failed. Check your username and password.');
      }
    } catch (error) {
      console.error('Error during login:', error);
    }
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    // Puteți adăuga alte acțiuni legate de delogare aici
  };

  return (
    <div className="auth-container">
      {isLoggedIn ? (
        <div>
          <p>Bine ai venit, {username}!</p>
          <button className="logout-btn" onClick={handleLogout}>
            Deconectare
          </button>
        </div>
      ) : (
        <div>
          <label>
            Nume de utilizator:
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
          </label>
          <br />
          <label>
            Parolă:
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </label>
          <br />
          <button onClick={handleLogin}>Autentificare</button>
        </div>
      )}
    </div>
  );
};

export default Authentication;
