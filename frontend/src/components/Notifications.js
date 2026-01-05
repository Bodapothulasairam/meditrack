import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Notifications.css';

function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchNotifications();
  }, []);

  const fetchNotifications = async () => {
    try {
      const response = await api.get('/notifications');
      setNotifications(response.data);
    } catch (error) {
      console.error('Error fetching notifications:', error);
    } finally {
      setLoading(false);
    }
  };

  const markAsRead = async (id) => {
    try {
      await api.put(`/notifications/${id}/read`);
      fetchNotifications();
    } catch (error) {
      console.error('Error marking notification as read:', error);
    }
  };

  if (loading) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h1>Notifications</h1>
      {notifications.length === 0 ? (
        <div className="card">No notifications available.</div>
      ) : (
        <div className="notifications-list">
          {notifications.map((notification) => (
            <div
              key={notification.id}
              className={`card notification-card ${!notification.read ? 'unread' : ''}`}
            >
              <div className="notification-header">
                <h3>{notification.title}</h3>
                {!notification.read && (
                  <button
                    onClick={() => markAsRead(notification.id)}
                    className="btn btn-secondary btn-sm"
                  >
                    Mark as Read
                  </button>
                )}
              </div>
              <p className="notification-message">{notification.message}</p>
              <p className="notification-meta">
                <span className="notification-type">{notification.type}</span>
                <span className="notification-date">
                  {new Date(notification.createdAt).toLocaleString()}
                </span>
              </p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Notifications;
