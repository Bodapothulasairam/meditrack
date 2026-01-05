import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import api from '../services/api';
import './Profile.css';

function Profile() {
  const { patient: authPatient } = useSelector((state) => state.auth);
  const [patient, setPatient] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({});

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const response = await api.get('/patients/me');
      setPatient(response.data);
      setFormData(response.data);
    } catch (error) {
      console.error('Error fetching profile:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.put('/patients/me', formData);
      setPatient(response.data);
      setEditing(false);
      alert('Profile updated successfully!');
    } catch (error) {
      console.error('Error updating profile:', error);
      alert('Failed to update profile');
    }
  };

  if (loading) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h1>My Profile</h1>
      <div className="card">
        <div className="profile-header">
          <h2>{patient?.firstName} {patient?.lastName}</h2>
          <button
            onClick={() => setEditing(!editing)}
            className="btn btn-primary"
          >
            {editing ? 'Cancel' : 'Edit Profile'}
          </button>
        </div>

        {editing ? (
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>First Name</label>
              <input
                type="text"
                value={formData.firstName || ''}
                onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Last Name</label>
              <input
                type="text"
                value={formData.lastName || ''}
                onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                value={formData.email || ''}
                disabled
              />
            </div>
            <div className="form-group">
              <label>Phone Number</label>
              <input
                type="tel"
                value={formData.phoneNumber || ''}
                onChange={(e) => setFormData({ ...formData, phoneNumber: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Date of Birth</label>
              <input
                type="date"
                value={formData.dateOfBirth || ''}
                onChange={(e) => setFormData({ ...formData, dateOfBirth: e.target.value })}
              />
            </div>
            <div className="form-group">
              <label>Address</label>
              <input
                type="text"
                value={formData.address || ''}
                onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              />
            </div>
            <div className="form-group">
              <label>Emergency Contact</label>
              <input
                type="text"
                value={formData.emergencyContact || ''}
                onChange={(e) => setFormData({ ...formData, emergencyContact: e.target.value })}
              />
            </div>
            <button type="submit" className="btn btn-primary">Save Changes</button>
          </form>
        ) : (
          <div className="profile-details">
            <p><strong>Email:</strong> {patient?.email}</p>
            <p><strong>Phone Number:</strong> {patient?.phoneNumber}</p>
            <p><strong>Date of Birth:</strong> {patient?.dateOfBirth || 'Not provided'}</p>
            <p><strong>Address:</strong> {patient?.address || 'Not provided'}</p>
            <p><strong>Emergency Contact:</strong> {patient?.emergencyContact || 'Not provided'}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default Profile;
