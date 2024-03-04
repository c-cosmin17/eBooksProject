import React, { useState, useEffect } from 'react';
import './cart.css';
import { useNavigate } from 'react-router-dom';
import bookcover from '../../assets/bookcover.png';

const Cart = () => {
  // Starea pentru a stoca articolele din coșul de cumpărături
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();

  // Obțineți id-ul utilizatorului și token-ul de autentificare din stocarea sesiunii
  const userId = sessionStorage.getItem('id');
  const token = sessionStorage.getItem('authToken');

  // Obțineți articolele din coșul de cumpărături când componenta este montată sau când se schimbă id-ul utilizatorului sau token-ul
  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await fetch(`http://localhost:9191/shoppingCart/${userId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });
        const data = await response.json();
        console.log(data);
        setCartItems(data);
      } catch (error) {
        console.error('Eroare la preluarea articolelor din coș:', error);
      }
    };

    fetchCartItems();
  }, [userId, token]);

  // Funcție pentru eliminarea unui articol din coș
  const handleRemoveFromCart = async (itemId) => {
    const response = await fetch(`http://localhost:9191/deleteShoppingCart`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify({
        customerId: userId,
        bookId: itemId,
      }),
    });
    const updatedCart = cartItems.filter((item) => item.id !== itemId);
    setCartItems(updatedCart);
  };

  // Funcție pentru a reveni la pagina de cărți
  const handleReturnToBooks = () => {
    navigate('/books'); // Navighează înapoi la pagina de cărți
  };

  // Funcție pentru confirmarea comenzii
  const handleOrderConfirmation = async () => {
    try {
      console.log(cartItems);
      // Pregătește datele cărților achiziționate (modificați după nevoie)
      const purchasedBooksData = cartItems.map(item => ({
        customerId: userId,
        bookId: item.id,
      }));
  
      // Trimite datele cărților achiziționate către server
      const purchasedBooksResponse = await fetch(`http://localhost:9191/addPurchasedBooks`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(purchasedBooksData),
      });
  
      if (purchasedBooksResponse.ok) {
        // Acum, ștergeți coșul de cumpărături
        const deleteCartResponse = await fetch(`http://localhost:9191/deleteShoppingCart/${userId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });
  
        if (deleteCartResponse.ok) {
          // Setează cartItems la un array gol după actualizările de succes de pe server
          setCartItems([]);
  
          // Afișează un mesaj utilizatorului
          alert('Comanda dumneavoastră a fost plasată cu succes!');
  
          // Redirecționează către pagina downloadBooks
          navigate('/downloadBooks');
        } else {
          console.error('Eșec la ștergerea coșului de cumpărături.');
        }
      } else {
        console.error('Eșec la adăugarea cărților achiziționate.');
      }
    } catch (error) {
      console.error('Eroare în timpul confirmării comenzii:', error);
    }
  };
  
  // Funcție pentru calcularea totalului
  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + parseFloat(item.price), 0).toFixed(2);
  };

  // Verificați dacă coșul este gol și afișați un mesaj corespunzător
  if (!cartItems || cartItems.length === undefined || cartItems.length === 0) {
    return (
      <div className="empty-cart-container">
        <h2>Coș de cumpărături</h2>
        <p>Coșul dumneavoastră de cumpărături este gol.</p>
        <div className="empty-back-to-books">
          <button onClick={handleReturnToBooks}>Înapoi la catalog</button>
        </div>
      </div>
    );
  }

  // Afișează coșul de cumpărături cu articolele și butoanele corespunzătoare
  return (
    <div className="cart-container">
      <h2>Coș de cumpărături</h2>
      <ul className="cart-items">
        {cartItems.map((item) => (
          <li key={item.id} className="cart-item-details">
            <img
              src={`http://localhost:9191/covers/${item.imagePath}` || bookcover}
              alt={item.title}
              className="cart-item-cover"
            />
            <div>
              <p>{item.title}</p>
              <p className="cart-item-author">{item.author}</p>
              <p className="cart-item-price">{item.price} lei</p>
            </div>
            <button onClick={() => handleRemoveFromCart(item.id)}>Elimină</button>
          </li>
        ))}
      </ul>
      {cartItems.length > 0 && (
        <div className="cart-total">
          <p>Total: {calculateTotal()} lei</p>
          <button onClick={handleOrderConfirmation}>Confirmă comanda</button>
        </div>
      )}
      <div className="back-to-books">
        <button onClick={handleReturnToBooks}>Înapoi la catalog</button>
      </div>
    </div>
  );
};

export default Cart;
