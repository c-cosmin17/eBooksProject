import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './book.css'; 
import bookcover from '../../assets/bookcover.png'; 

const Book = () => {
  const { id } = useParams();
  const [book, setBook] = useState(null);
  useEffect(() => {
    const fetchData = async () => {
      try {
        // Obțineți token-ul din sessionStorage
        const token = sessionStorage.getItem('authToken');
        // Preluare detalii carte din API-ul backend pe baza ID-ului cărții
        const response = await fetch(`http://localhost:9191/book/${id}`);
        const data = await response.json();
  
        // Preluare detalii tip și categorie pe baza typeId și categoryId ale cărții
        const typeResponse = await fetch(`http://localhost:9191/typeById/${data.typeId}`);
        const categoryResponse = await fetch(`http://localhost:9191/categoryById/${data.categoryId}`);
        const publisherResponse = await fetch(`http://localhost:9191/publisherById/${data.publisherId}`);

        const typeData = await typeResponse.json();
        const categoryData = await categoryResponse.json();
        const publisherData = await publisherResponse.json();
        // Setare detalii carte cu informații despre tip și categorie
        setBook({ ...data, type: typeData.name, category: categoryData.name, publisher: publisherData.name });
      } catch (error) {
        console.error('Eroare la preluarea detaliilor cărții:', error);
      }
    };
  
    fetchData();
  }, [id]);

  const navigate = useNavigate();

  const handleReturnToBooks = () => {
    navigate('/books'); // Navigare înapoi la pagina cu cărți
  };


  const userId = sessionStorage.getItem('id');
  const token = sessionStorage.getItem('authToken');
  const isAuthenticated = token !== null;
  console.log(userId);

  const handleAddToCart = async () => {
    if (!userId) {
      console.error('ID utilizator indisponibil.');
      return;
    }

    try {
      const response = await fetch(`http://localhost:9191/addShoppingCart`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          customerId: userId,
          bookId: book.id,
        }),
      });
      navigate('/cart');
    } catch (error) {
      console.error('A apărut o eroare la adăugarea cărții în coș:', error);
    }
  };

  if (!book) {
    return <div>Se încarcă...</div>;
  }

  return (
    <div className="book-details-container">
      <div className="book-content">
      <img src={`http://localhost:9191/covers/${book.imagePath}` || bookcover} alt={book.title} className="book-cover" />
        <div className="book-details">
          <h2>{book.title}</h2>
          <p className="author">Autor: {book.author}</p>
          <p className="book-description">Descriere: {book.bookDescription}</p>
          <p className="author-description">Descriere autor: {book.authorDescription}</p>
          <p className="category">Categorie: {book.category}</p>
          <p className="type">Tip: {book.type}</p>
          <p className="publisher">Editura: {book.publisher}</p>
          <p className="price">Preț: {book.price} lei</p>     
        </div>
      </div>
      <div className="buttons-container">
        <button onClick={handleReturnToBooks} className="return-button">
          Înapoi la catalog
        </button>
        {isAuthenticated && (
          <button onClick={handleAddToCart} className="add-to-cart-button">
          Adaugă în coș
        </button>
        )}
      </div>
    </div>
  );
};

export default Book;
