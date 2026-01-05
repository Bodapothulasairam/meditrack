import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Appointments.css';

function Appointments() {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    doctorName: '',
    department: '',
    appointmentDateTime: '',
    reason: '',
  });

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    try {
      const response = await api.get('/appointments');
      setAppointments(response.data);
    } catch (error) {
      console.error('Error fetching appointments:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/appointments', formData);
      setShowForm(false);
      setFormData({ doctorName: '', department: '', appointmentDateTime: '', reason: '' });
      fetchAppointments();
    } catch (error) {
      console.error('Error booking appointment:', error);
      alert('Failed to book appointment');
    }
  };

  const handleCancel = async (id) => {
    if (window.confirm('Are you sure you want to cancel this appointment?')) {
      try {
        await api.put(`/appointments/${id}/cancel`);
        fetchAppointments();
      } catch (error) {
        console.error('Error cancelling appointment:', error);
      }
    }
  };

  if (loading) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <div className="page-header">
        <h1>My Appointments</h1>
        <button onClick={() => setShowForm(!showForm)} className="btn btn-primary">
          {showForm ? 'Cancel' : 'Book Appointment'}
        </button>
      </div>

      {showForm && (
        <div className="card">
          <h2>Book New Appointment</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Doctor Name</label>
              <input
                type="text"
                value={formData.doctorName}
                onChange={(e) => setFormData({ ...formData, doctorName: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Department</label>
              <input
                type="text"
                value={formData.department}
                onChange={(e) => setFormData({ ...formData, department: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Date & Time</label>
              <input
                type="datetime-local"
                value={formData.appointmentDateTime}
                onChange={(e) => setFormData({ ...formData, appointmentDateTime: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Reason</label>
              <textarea
                value={formData.reason}
                onChange={(e) => setFormData({ ...formData, reason: e.target.value })}
                rows="3"
              />
            </div>
            <button type="submit" className="btn btn-primary">Book Appointment</button>
          </form>
        </div>
      )}

      <div className="appointments-list">
        {appointments.length === 0 ? (
          <div className="card">No appointments found. Book your first appointment!</div>
        ) : (
          appointments.map((apt) => (
            <div key={apt.id} className="card appointment-card">
              <div className="appointment-header">
                <h3>{apt.doctorName}</h3>
                <span className={`status-badge status-${apt.status.toLowerCase()}`}>
                  {apt.status}
                </span>
              </div>
              <div className="appointment-details">
                <p><strong>Department:</strong> {apt.department}</p>
                <p><strong>Date & Time:</strong> {new Date(apt.appointmentDateTime).toLocaleString()}</p>
                {apt.reason && <p><strong>Reason:</strong> {apt.reason}</p>}
              </div>
              {apt.status !== 'CANCELLED' && apt.status !== 'COMPLETED' && (
                <button
                  onClick={() => handleCancel(apt.id)}
                  className="btn btn-secondary"
                >
                  Cancel Appointment
                </button>
              )}
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Appointments;
