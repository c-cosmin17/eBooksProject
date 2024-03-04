import React, { useState, useEffect } from 'react';
import './downloadBooks.css';
import bookcover from '../../assets/bookcover.png';
import { useNavigate } from 'react-router-dom';

const DownloadBooks = () => {
  // Starea pentru a stoca lista de cărți achiziționate
  const [purchasedBooks, setPurchasedBooks] = useState([]);

  // Obțineți id-ul utilizatorului și token-ul de autentificare din stocarea sesiunii
  const userId = sessionStorage.getItem('id');
  const token = sessionStorage.getItem('authToken');

  // Obțineți cărțile achiziționate când componenta este montată sau când se schimbă id-ul utilizatorului sau token-ul
  useEffect(() => {
    const fetchPurchasedBooks = async () => {
      try {
        // Obțineți cărțile achiziționate de la server folosind userId și token
        const response = await fetch(`http://localhost:9191/purchasedBooks/${userId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });

        // Dacă răspunsul este reușit, actualizați lista de cărți achiziționate
        if (response.ok) {
          const data = await response.json();
          setPurchasedBooks(data);
        } else {
          console.error('Eșec la preluarea cărților achiziționate');
        }
      } catch (error) {
        console.error('Eroare la preluarea cărților achiziționate:', error);
      }
    };

    fetchPurchasedBooks();
  }, [userId, token]);

  const navigate = useNavigate();
  const handleReturnToBooks = () => {
    navigate('/books'); // Navighează înapoi la pagina de cărți
  };

  if (!purchasedBooks || purchasedBooks.length === undefined || purchasedBooks.length === 0) {
    return (
      <div className="empty-cart-container">
        <h2>Biblioteca virtuala</h2>
        <p>Biblioteca dumneavoastră virtuală nu conține nicio carte.</p>
        <div className="empty-back-to-books">
          <button onClick={handleReturnToBooks}>Înapoi la catalog</button>
        </div>
      </div>
    );
  }
  // Afișează lista de cărți achiziționate în interfața utilizatorului
  return (
    <div className="book-container-download">
      <ul>
        {purchasedBooks.map((book) => (
          <li key={book.id} className="book-item-download">
            <div className="book-details-download">
              {/* Afișează imaginea copertei cărții */}
              <img src={`http://localhost:9191/covers/${book.imagePath}` || bookcover} alt="Book Cover" className="book-cover-download" />
              <div>
                {/* Afișează titlul și autorul cărții */}
                <p>{book.title}</p>
                <p>{book.author}</p>
              </div>
            </div>
            {/* Adaugă un link pentru descărcarea cărții în format PDF */}
            <a href={`http://localhost:9191/covers/${encodeURIComponent(book.pdfPath)}`} download>
                 Citește
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default DownloadBooks;
