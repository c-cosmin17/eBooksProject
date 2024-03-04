import React, { useState } from 'react'

import { Books, Header} from './containers';
import { Book, Manage, Navbar, Registration, Authentication, Cart, AddBook, ManageBooks, EditBook, DownloadBooks, ManageCategories, ManageTypes, ManagePublishers} from './components';
import './App.css';
import { Route, Routes } from 'react-router-dom';

const App = () => {
  const [cart, setCart] = useState([]);

  // Funcție pentru adăugarea unui element în coș
  const handleAddToCart = (book) => {
    setCart([...cart, book]);
  };

  return (
    <div className='App'>
        <Navbar />
        <Routes>
          <Route path='/' element={<Header />} />
          <Route path='/register' element={<Registration />} />
          <Route path='/login' element={<Authentication />} />
          <Route path='/book/:id' element={<Book />} />
          <Route path='/books' element={<Books />} />
          <Route path='/cart' element={<Cart />} />
          <Route path='/manage' element={<Manage />} />
          <Route path='/manage/books' element={<ManageBooks />} />
          <Route path='/manage/books/addBook' element={<AddBook />} />
          <Route path='/manage/books/editBook/:bookId' element={<EditBook />} />
          <Route path='/manage/categories' element={<ManageCategories />} />
          <Route path='/manage/types' element={<ManageTypes />} />
          <Route path='/manage/publishers' element={<ManagePublishers />} />
          <Route path='/downloadBooks' element={<DownloadBooks />} />
        </Routes>
    </div>
  );
}

export default App
