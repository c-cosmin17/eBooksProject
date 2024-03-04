import React, { useState, useEffect } from 'react';
import './books.css'; 
import bookcover from '../../assets/bookcover.png';
import Filter from '../../components/filter/Filter';
import Sort from '../../components/sort/Sort';
import Search from '../../components/search/Search';
import { Link } from 'react-router-dom';


const Books = () => {
 
//Se extrag cartile, tipurile si categoriile din baza de date.
const [booksData, setBooks] = useState([]);
const [categories, setCategories] = useState([]);
const [types, setTypes] = useState([]);
useEffect(() => {
  fetch('http://localhost:9191/Books')
    .then((response) => response.json())
    .then((data) => setBooks(data))
    .catch((error) => console.error('Error fetching books:', error));

    fetch('http://localhost:9191/categories')
      .then((response) => response.json())
      .then((data) => setCategories(data))
      .catch((error) => console.error('Error fetching categories:', error));

      fetch('http://localhost:9191/types')
      .then((response) => response.json())
      .then((data) => setTypes(data))
      .catch((error) => console.error('Error fetching categories:', error));
}, []);


//logica pentru paginare
//se calculeaza numarul de carti de pe fiecare pagina
const booksPerPage = 6;
const rowsPerPage = 1;
const totalBooks = booksData.length;
const totalPages = Math.ceil(totalBooks / (booksPerPage * rowsPerPage));
const [currentPage, setCurrentPage] = useState(1);
const startIndex = (currentPage - 1) * booksPerPage * rowsPerPage;
const endIndex = startIndex + booksPerPage * rowsPerPage;


//se initializeaza componentele de sortare, filtrare si cautare
const [filter, setFilter] = useState({ category: 'all', type: 'all' });
const [sort, setSort] = useState({ field: 'price', order: 'asc' });
const [search, setSearch] = useState('');




const handleFilterChange = (name, value) => {
  setFilter((prevFilter) => ({ ...prevFilter, [name]: value }));
};

const handleSortChange = ({ field, order }) => {
  setSort({ field, order });
};

const handleSearchChange = (value) => {
  setSearch(value);
};
//se filtreaza si sorteaza cartile extrase din baza de date
const filteredBooks = booksData
  .filter((book) => filter.category === 'all' || book.category === filter.category)
  .filter((book) => filter.type === 'all' || book.type === filter.type)
  .filter(
    (book) =>
      book.title.toLowerCase().includes(search.toLowerCase()) ||
      book.author.toLowerCase().includes(search.toLowerCase())
  )
  .sort((a, b) => {
    
    const { field, order } = sort;
    console.log(field,order);
    if (field === 'price') {
      const priceA = parseFloat(a.price);
      const priceB = parseFloat(b.price);
      if (!isNaN(priceA) && !isNaN(priceB)) {
        return order === 'asc' ? priceA - priceB : priceB - priceA;
      } else {
        return String(a.price).localeCompare(String(b.price));
      }
    } else if (field === 'title') {
      return order === 'asc' ? a.title.localeCompare(b.title) : b.title.localeCompare(a.title);
    } else if (field === 'name') {
      console.log(a.author,b.author);
      return order === 'asc' ? a.author.localeCompare(b.author) : b.author.localeCompare(a.author);
    }
    return 0;
  });

const handlePrevPage = () => {
  setCurrentPage((prevPage) => Math.max(prevPage - 1, 1));
};

const handleNextPage = () => {
  setCurrentPage((prevPage) => Math.min(prevPage + 1, totalPages));
};

return (
  <div className="books-container">
    <div className="sidebar">
      <Filter categories={categories} types={types} onFilterChange={handleFilterChange} />
      <Sort onSortChange={handleSortChange} />
    </div>
    <div className="main-content">
      <Search onSearchChange={handleSearchChange} />
      <div className="pagination">
        <button onClick={handlePrevPage} disabled={currentPage === 1}>
          Anterior
        </button>
        <span>{`Page ${currentPage} of ${totalPages}`}</span>
        <button onClick={handleNextPage} disabled={currentPage === totalPages}>
          Următor
        </button>
      </div>
      <ul className="books-list">
      {filteredBooks.slice(startIndex, endIndex).map((book) => (
        <li key={book.id} className="books-item">
          <Link to={`/book/${book.id}`}>
            <div className="books-details">
            <img src={`http://localhost:9191/covers/${book.imagePath}` || bookcover} alt={book.title} className="books-cover" />
              <p>{book.title}</p>
              <p className="author">{book.author}</p>
              <p className="price">{book.price} lei</p>
            </div>
          </Link>
        </li>
      ))}
      </ul>
    </div>
  </div>
);
};

export default Books;