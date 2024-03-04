import React, { useState, useEffect } from 'react';
import './manageBooks.css';
import bookcover from '../../assets/bookcover.png';
import { useParams, useNavigate } from 'react-router-dom';

const ManageBooks = () => {
  const [booksData, setBooks] = useState([]);

  const token = sessionStorage.getItem('authToken');


  //extragem cartile din baza de date
  useEffect(() => {
    fetch('http://localhost:9191/manager/books', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then((response) => response.json())
      .then((data) => setBooks(data))
      .catch((error) => console.error('Error fetching books:', error));
  }, []);

  const handleEdit = (bookId) => {
    navigate(`/manage/books/editBook/${bookId}`);
  };
  //stergem cartea din baza de date
  const handleDelete = (bookId) => {
    fetch(`http://localhost:9191/manager/deleteBook/${bookId}`, {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Failed to delete book with id ${bookId}`);
        }
        setBooks((prevBooks) => prevBooks.filter((book) => book.id !== bookId));
      })
      .catch((error) => console.error('Error deleting book:', error));
  };
  const navigate = useNavigate();
  const handleAddBook = () => {
    navigate("/manage/books/addBook");
  };

  return (
    <div className="book-container-manage">
      <button className="book-container-manage-button" onClick={handleAddBook}>Adaugă carte</button>
      {booksData.map((book) => (
        <div key={book.id} className="book-item-manage">
          <div className="book-details-manage">
          <img src={`http://localhost:9191/covers/${book.imagePath}` || bookcover} alt="Book Cover" className="book-cover-manage" />
            <p>Titlu: {book.title}</p>
            <p>Autor: {book.author}</p>
            <p>Preț: {book.price} lei</p>
          </div>
          <div>
            <button className="book-container-manage-button" onClick={() => handleEdit(book.id)}>Editează</button>
            <button className="book-container-manage-button" onClick={() => handleDelete(book.id)}>Sterge</button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ManageBooks;