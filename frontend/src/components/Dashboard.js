import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import api from '../services/api';
import './Dashboard.css';

function Dashboard() {
  const { patient } = useSelector((state) => state.auth);
  const [stats, setStats] = useState({
    upcomingAppointments: 0,
    recentLabResults: 0,
    unreadNotifications: 0,
  });

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const [appointments, labResults, notifications] = await Promise.all([
          api.get('/appointments'),
          api.get('/lab-results'),
          api.get('/notifications/unread'),
        ]);
        
        const upcoming = appointments.data.filter(
          (apt) => new Date(apt.appointmentDateTime) > new Date() && 
          apt.status !== 'CANCELLED'
        ).length;
        
        setStats({
          upcomingAppointments: upcoming,
          recentLabResults: labResults.data.length,
          unreadNotifications: notifications.data.length,
        });
      } catch (error) {
        console.error('Error fetching stats:', error);
      }
    };
    
    fetchStats();
  }, []);

  return (
    <div className="container">
      <h1>Welcome, {patient?.firstName}!</h1>
      <div className="stats-grid">
        <div className="stat-card">
          <h3>Upcoming Appointments</h3>
          <p className="stat-number">{stats.upcomingAppointments}</p>
        </div>
        <div className="stat-card">
          <h3>Lab Results</h3>
          <p className="stat-number">{stats.recentLabResults}</p>
        </div>
        <div className="stat-card">
          <h3>Unread Notifications</h3>
          <p className="stat-number">{stats.unreadNotifications}</p>
        </div>
      </div>
      <div className="card">
        <h2>Quick Actions</h2>
        <div className="quick-actions">
          <a href="/appointments" className="action-btn">Book Appointment</a>
          <a href="/lab-results" className="action-btn">View Lab Results</a>
          <a href="/notifications" className="action-btn">Check Notifications</a>
          <a href="/profile" className="action-btn">Update Profile</a>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
