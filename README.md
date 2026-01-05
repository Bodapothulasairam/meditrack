# MediTrack - Healthcare Patient Portal

A cloud-native patient management system with secure appointment booking, lab results viewing, and HIPAA-compliant communication.

## Features

- Patient appointment booking system
- Secure lab results viewing
- HIPAA-compliant communication
- Patient data management
- Notification system

## Tech Stack

- **Backend**: Java 17, Spring Boot 3, Spring Security
- **Frontend**: React 18, Redux, React Router
- **Databases**: PostgreSQL, MongoDB
- **Security**: JWT Authentication, HIPAA Compliance

## Project Structure

```
meditrack/
├── backend/          # Spring Boot application
├── frontend/         # React application
└── README.md
```

## Getting Started

### Prerequisites

- Java 17+
- Node.js 18+
- PostgreSQL 14+
- MongoDB 6+

### Backend Setup

```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

## API Endpoints

- `POST /api/auth/login` - Patient login
- `GET /api/patients/{id}` - Get patient data
- `GET /api/appointments` - Get appointments
- `POST /api/appointments` - Book appointment
- `GET /api/lab-results/{patientId}` - Get lab results
- `POST /api/notifications` - Send notification

## License

MIT
