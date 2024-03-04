import React, { useState } from 'react';
import axios from 'axios';
import './header.css';

const Header = () => {
  const [email, setEmail] = useState('');

  const isValidEmail = (email) => {
    // Expresia regulată pentru verificarea formatului adresei de e-mail
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleSubscribe = async () => {
    try {
      //daca nu este introdus emailul nu se poate actiona butonul
      if (!email) {
        alert('Te rugăm să introduci adresa de email.');
        return;
      }
      //se verifica daca email-ul este valid
      if (!isValidEmail(email)) {
        alert('Te rugăm să introduci o adresă de email validă.');
        return;
      }

      const response = await axios.post('http://localhost:9191/addSubscriber', { email });
      if(response.data === ""){
        alert('Sunteti deja abonat!');
        setEmail('');
        return;
      }
      
      // Afiseaza alerta de succes
      alert('Abonare cu succes!');

      // Sterge adresa de e-mail din placeholder și resetează starea
      setEmail('');

    } catch (error) {
      console.error('Error adding subscriber:', error);
      alert('A apărut o eroare la abonare. Vă rugăm să încercați din nou.');
    }
  };

  return (
    <div className="gpt3__header section__padding" id="home">
      <div className="gpt3__header-content">
        <h1 className="gradient__text">Bine ai venit în paradisul virtual al cărților...</h1>
        <p>Vrei să fii la curent cu ultimele noutăți?</p>
        <p>Abonează-te la newsletter-ul nostru...</p>
        <div className="gpt3__header-content__input">
          <input
            type="email"
            placeholder="Adresa dumneavoastră de e-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button type="button" onClick={handleSubscribe}>
            Abonează-te
          </button>
        </div>
      </div>
    </div>
  );
};

export default Header;
