// Search.js
import React from 'react';
import './search.css';

const Search = ({ onSearchChange }) => {
  const handleSearchChange = (event) => {
    onSearchChange(event.target.value);
  };

  return (
    <div className="search-container">
      <input
        type="text"
        name="search"
        placeholder="Căutare dupa titlu sau autor"
        onChange={handleSearchChange}
      />
    </div>
  );
};

export default Search;
