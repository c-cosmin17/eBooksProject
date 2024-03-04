import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './navbar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart, faBookOpen, faSignOutAlt } from '@fortawesome/free-solid-svg-icons'; // Adaugă iconița pentru cărți și logout


const Navbar = () => {

  //stocam tokenul si autoritarea utilizatorului
  const token = sessionStorage.getItem('authToken');
  const role = sessionStorage.getItem('role');
  const isAuthenticated = token !== null;
  const isUser = role === "USER";

  //folosim navigate pentru a schimba intre componente
  const navigate = useNavigate();


  //cand ne deconectam curatam toate datele
  const handleLogout = () => {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('role');
    navigate('/');
  };

  const handleManage = () => {
    navigate('/manage');
  };

  return (
    <div className="navbar">
      <ul className="navbar-links">
        <li>
          <Link to="/" className="nav-link">
            Acasă
          </Link>
        </li>
        <li>
          <Link to="/books" className="nav-link">
            Cărți
          </Link>
        </li>
      </ul>
      <ul className="auth-section">
        {isAuthenticated && !isUser && (
          <li>
            <span onClick={handleManage} className="auth-link">
              Administrare
            </span>
          </li>
        )}
        {isAuthenticated && isUser && (
          <li>
            <Link to="/downloadBooks" className="nav-link"> {/* Adaugă link pentru DownloadBooks */}
              <FontAwesomeIcon icon={faBookOpen} className="books-icon" />
            </Link>
          </li>
        )}
        {isAuthenticated && isUser && (
          <li>
            <Link to="/cart" className="nav-link">
              <FontAwesomeIcon icon={faShoppingCart} className="cart-icon" />
            </Link>
          </li>
        )}
        {isAuthenticated ? (
          <>
            <li>
              <Link to="/" onClick={handleLogout} className="auth-link"> 
                <FontAwesomeIcon icon={faSignOutAlt} className="logout-icon" />
              </Link>
            </li>
          </>
        ) : (
          <>
            <li>
              <Link to="/login" className="auth-link">
                Autentificare
              </Link>
            </li>
            <li>
              <Link to="/register" className="auth-link">
                Înregistrare
              </Link>
            </li>
          </>
        )}
      </ul>
    </div>
  );
};

export default Navbar;
