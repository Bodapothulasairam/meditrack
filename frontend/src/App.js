import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import Appointments from './components/Appointments';
import LabResults from './components/LabResults';
import Notifications from './components/Notifications';
import Profile from './components/Profile';
import Navbar from './components/Navbar';
import './App.css';

function App() {
  const { isAuthenticated } = useSelector((state) => state.auth);

  return (
    <div className="App">
      {isAuthenticated && <Navbar />}
      <Routes>
        <Route 
          path="/login" 
          element={!isAuthenticated ? <Login /> : <Navigate to="/dashboard" />} 
        />
        <Route 
          path="/register" 
          element={!isAuthenticated ? <Register /> : <Navigate to="/dashboard" />} 
        />
        <Route 
          path="/dashboard" 
          element={isAuthenticated ? <Dashboard /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/appointments" 
          element={isAuthenticated ? <Appointments /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/lab-results" 
          element={isAuthenticated ? <LabResults /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/notifications" 
          element={isAuthenticated ? <Notifications /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/profile" 
          element={isAuthenticated ? <Profile /> : <Navigate to="/login" />} 
        />
        <Route path="/" element={<Navigate to={isAuthenticated ? "/dashboard" : "/login"} />} />
      </Routes>
    </div>
  );
}

export default App;
