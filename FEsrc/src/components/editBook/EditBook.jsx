// EditBook.jsx

import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './editBook.css';  // Import the CSS file

const EditBook = () => {
  const token = sessionStorage.getItem('authToken');
  const { bookId } = useParams();
  const navigate = useNavigate();
  const [bookData, setBookData] = useState({
    categoryId: 0,
    typeId: 0,
    publisherId: 0,
    title: '',
    author: '',
    price: '',
    authorDescription: '',
    bookDescription: '',
  });

  useEffect(() => {
    fetch(`http://localhost:9191/manager/getBook/${bookId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then((response) => response.json())
      .then((data) => setBookData(data))
      .catch((error) => console.error('Error fetching book details:', error));
  }, [bookId, token]);

  const handleChange = (e) => {
    setBookData({ ...bookData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:9191/manager/updateBook/${bookId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(bookData)
      });

      if (!response.ok) {
        throw new Error(`Failed to update book with id ${bookId}`);
      }
      navigate('/manage/books');
    } catch (error) {
      console.error('Error updating book details:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="heading">Editează carte</h2>
      <form onSubmit={handleSubmit} className="form">
        <label className="label">
          Titlu:
          <input type="text" name="title" value={bookData.title} onChange={handleChange} className="input" />
        </label>
        <br />
        <label className="label">
          Autor:
          <input type="text" name="author" value={bookData.author} onChange={handleChange} className="input" />
        </label>
        <br />
        <label className="label">
          Preț:
          <input type="text" name="price" value={bookData.price} onChange={handleChange} className="input" />
        </label>
        <br />
        <label className="label">
          Descrierea autorului:
          <textarea name="authorDescription" value={bookData.authorDescription} onChange={handleChange} className="textarea" />
        </label>
        <br />
        <label className="label">
          Descrierea cărții:
          <textarea name="bookDescription" value={bookData.bookDescription} onChange={handleChange} className="textarea" />
        </label>
        <br />
        <label className="label">
          ID Categorie:
          <input type="text" name="categoryId" value={bookData.categoryId} onChange={handleChange} className="input" />
        </label>
        <br />
        <label className="label">
          ID Tip:
          <input type="text" name="typeId" value={bookData.typeId} onChange={handleChange} className="input" />
        </label>
        <br />
        <label className="label">
          ID Editura:
          <input type="text" name="publisherId" value={bookData.publisherId} onChange={handleChange} className="input" />
        </label>
        <br />
        <button type="submit" className="button">Salvează</button>
      </form>
    </div>
  );
};

export default EditBook;
