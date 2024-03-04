import { Link } from 'react-router-dom';
import './manage.css'

const Manage = () => {
  return (
    <div className="manage-container">
      <div className="manage-buttons">
        <Link to="/manage/books">
          <button>Administrează Cărți</button>
        </Link>
        <Link to="/manage/types">
          <button>Administrează Tipuri</button>
        </Link>
        <Link to="/manage/categories">
          <button>Administrează Categorii</button>
        </Link>
        <Link to="/manage/publishers">
          <button>Administrează Publicanți</button>
        </Link>
      </div>
    </div>
  );
};

export default Manage;
