import './filter.css'
const Filter = ({ categories, types, onFilterChange }) => {
  const handleCategoryChange = (event) => {
    onFilterChange('category', event.target.value);
  };

  const handleTypeChange = (event) => {
    onFilterChange('type', event.target.value);
  };

  return (
    <div className="filter-container">
      <div className="category-filters">
        <p>Categorie:</p>
        <button
          className="filter-button"
          value="all"
          onClick={() => onFilterChange('category', 'all')}
        >
          Toate
        </button>
        {categories.map((category) => (
          <button
            key={category.id}
            className="filter-button"
            value={category.name}
            onClick={handleCategoryChange}
          >
            {category.name}
          </button>
        ))}
      </div>
      <div className="type-filters">
        <p>Tip:</p>
        <button
          className="filter-button"
          value="all"
          onClick={() => onFilterChange('type', 'all')}
        >
          Toate
        </button>
        {types.map((type) => (
          <button
            key={type.id}
            className="filter-button"
            value={type.name}
            onClick={handleTypeChange}
          >
            {type.name}
          </button>
        ))}
      </div>
    </div>
  );
};

export default Filter;
