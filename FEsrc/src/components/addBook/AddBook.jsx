import React, { useState } from 'react';
import './addBook.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddBook = () => {
  const [book, setBook] = useState({
    id: '',
    categoryId: '',
    typeId: '',
    publisherId: '',
    title: '',
    author: '',
    price: '',
    authorDescription: '',
    bookDescription: '',
    bookCover: null,
    pdfFile: null,
  });

  const navigate = useNavigate();

  const handleReturn = () => {
    navigate('/manage/books'); 
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setBook((prevBook) => ({
      ...prevBook,
      [name]: value,
    }));
  };

  const handleFileChange = (e, fileType) => {
    const file = e.target.files[0];
    setBook((prevBook) => ({
      ...prevBook,
      [fileType]: file,
    }));
  };

  const token = sessionStorage.getItem('authToken');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!book.title || !book.author || !book.price || !book.authorDescription || !book.bookDescription || !book.publisherId || !book.typeId || !book.categoryId || !book.bookCover || !book.pdfFile) {
      alert('Toate câmpurile sunt obligatorii.');
      return;
    }
    try {
      const requestData = {
        title: book.title,
        author: book.author,
        price: book.price,
        authorDescription: book.authorDescription,
        bookDescription: book.bookDescription,
        publisherId: book.publisherId,
        typeId: book.typeId,
        categoryId: book.categoryId,
      };

      const formData = new FormData();
      formData.append('book', new Blob([JSON.stringify(requestData)], { type: 'application/json' }));
      formData.append('bookCover', book.bookCover);
      formData.append('pdfFile', book.pdfFile);

      const response = await axios.post('http://localhost:9191/manager/addBook', formData, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });

      console.log('Book added successfully:', response.data);
      alert('Cartea a fost adaugată cu succes!');
      setBook({
        id: '',
        categoryId: '',
        typeId: '',
        publisherId: '',
        title: '',
        author: '',
        price: '',
        authorDescription: '',
        bookDescription: '',
        bookCover: null,
        pdfFile: null,
      });

    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  return (
    <div className="add-book-container">
      <h2 className="add-book-header">Add New Book</h2>
      <form onSubmit={handleSubmit} className="add-book-form">
        <label className="add-book-label">
          Titlu:
          <input
            type="text"
            name="title"
            value={book.title}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          Autor:
          <input
            type="text"
            name="author"
            value={book.author}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          Preț:
          <input
            type="text"
            name="price"
            value={book.price}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          Descrierea Autorului:
          <textarea
            name="authorDescription"
            value={book.authorDescription}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          Descrierea Cărții:
          <textarea
            name="bookDescription"
            value={book.bookDescription}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          ID Editura:
          <input
            type="text"
            name="publisherId"
            value={book.publisherId}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          ID Tip:
          <input
            type="text"
            name="typeId"
            value={book.typeId}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          ID Categorie:
          <input
            type="text"
            name="categoryId"
            value={book.categoryId}
            onChange={handleInputChange}
            required
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          Copertă:
          <input
            type="file"
            name="bookCover"
            onChange={(e) => handleFileChange(e, 'bookCover')}
            accept="image/*"
            className="add-book-input"
          />
        </label>
        <br />
        <label className="add-book-label">
          PDF:
          <input
            type="file"
            name="pdfFile"
            onChange={(e) => handleFileChange(e, 'pdfFile')}
            accept=".pdf"
            className="add-book-input"
          />
        </label>
        <br />
        <div className="add-book-button-container">
          <button type="submit" className="add-book-button">
            Adaugă carte
          </button>
          <button type="button" className="add-book-button" onClick={handleReturn}>
            Revenire
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddBook;
