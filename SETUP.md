# MediTrack Setup Guide

## Prerequisites

1. **Java 17+** - Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK](https://adoptium.net/)
2. **Node.js 18+** - Download from [Node.js](https://nodejs.org/)
3. **PostgreSQL 14+** - Download from [PostgreSQL](https://www.postgresql.org/download/)
4. **MongoDB 6+** - Download from [MongoDB](https://www.mongodb.com/try/download/community)
5. **Maven** (optional, Maven wrapper included)

## Database Setup

### PostgreSQL Setup

1. Create a database:
```sql
CREATE DATABASE meditrack;
```

2. Update `backend/src/main/resources/application.yml` with your PostgreSQL credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/meditrack
    username: your_username
    password: your_password
```

### MongoDB Setup

1. Start MongoDB service
2. Update `backend/src/main/resources/application.yml` with your MongoDB URI:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/meditrack
```

## Backend Setup

1. Navigate to backend directory:
```bash
cd meditrack/backend
```

2. Build and run:
```bash
# Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw clean install
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8080`

## Frontend Setup

1. Navigate to frontend directory:
```bash
cd meditrack/frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## API Endpoints

- `POST /api/auth/register` - Register new patient
- `POST /api/auth/login` - Patient login
- `GET /api/patients/me` - Get current patient data
- `GET /api/patients/{id}` - Get patient by ID
- `PUT /api/patients/me` - Update patient profile
- `GET /api/appointments` - Get all appointments
- `POST /api/appointments` - Book new appointment
- `GET /api/appointments/{id}` - Get appointment by ID
- `PUT /api/appointments/{id}/cancel` - Cancel appointment
- `GET /api/lab-results` - Get all lab results
- `GET /api/lab-results/{id}` - Get lab result by ID
- `GET /api/notifications` - Get all notifications
- `GET /api/notifications/unread` - Get unread notifications
- `PUT /api/notifications/{id}/read` - Mark notification as read

## Testing

1. Register a new patient account
2. Login with your credentials
3. Book an appointment
4. View lab results (requires admin to add test data)
5. Check notifications

## Troubleshooting

- **Port 8080 already in use**: Change the port in `application.yml`
- **Database connection error**: Verify PostgreSQL/MongoDB are running and credentials are correct
- **CORS errors**: Ensure backend CORS configuration allows `http://localhost:3000`
