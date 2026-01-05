import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from '../store/slices/authSlice';
import './Navbar.css';

function Navbar() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { patient } = useSelector((state) => state.auth);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/dashboard" className="navbar-brand">
          MediTrack
        </Link>
        <div className="navbar-menu">
          <Link to="/dashboard" className="navbar-link">Dashboard</Link>
          <Link to="/appointments" className="navbar-link">Appointments</Link>
          <Link to="/lab-results" className="navbar-link">Lab Results</Link>
          <Link to="/notifications" className="navbar-link">Notifications</Link>
          <Link to="/profile" className="navbar-link">Profile</Link>
          <div className="navbar-user">
            <span>{patient?.firstName} {patient?.lastName}</span>
            <button onClick={handleLogout} className="btn-logout">Logout</button>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
