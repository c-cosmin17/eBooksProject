// Sort.js
import React, { useState } from 'react';
import './sort.css';

const Sort = ({ onSortChange }) => {
  const [order, setOrder] = useState('asc');
  const [field, setField] = useState(null);

  const handleSortChange = (newField) => {
    if (field === newField) {
      // Dacă câmpul este același, inversăm ordinea
      setOrder((prevOrder) => (prevOrder === 'asc' ? 'desc' : 'asc'));
    } else {
      // Dacă schimbăm câmpul, resetăm ordinea la ascendent
      setOrder('asc');
    }

    setField(newField);
    onSortChange({ field: newField, order });
  };

  return (
    <div className="sort-container">
      <div className="sort-options">
        <p>Sortează după:</p>
        <button className="sort-button" onClick={() => handleSortChange('price')}>
          Preț
        </button>
        <button className="sort-button" onClick={() => handleSortChange('name')}>
          Nume
        </button>
        <button className="sort-button" onClick={() => handleSortChange('title')}>
          Titlu
        </button>
      </div>
    </div>
  );
};

export default Sort;
