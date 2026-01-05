@echo off
echo Setting up Git repository for MediTrack...

REM Check if we're in the meditrack directory
if not exist "README.md" (
    echo Error: Please run this script from the meditrack directory
    exit /b 1
)

REM Initialize git if not already initialized
if not exist ".git" (
    echo Initializing Git repository...
    git init
)

REM Add all files
echo Adding all files to Git...
git add .

REM Create initial commit
echo Creating initial commit...
git commit -m "Initial commit: MediTrack Healthcare Patient Portal

- Full-stack healthcare patient management system
- Backend: Spring Boot 3 + Java 17 with JWT authentication
- Frontend: React 18 + Redux
- Features: Appointment booking, lab results, notifications
- Databases: PostgreSQL + MongoDB
- HIPAA-compliant secure communication"

echo.
echo Git repository initialized and committed successfully!
echo.
echo Next steps:
echo 1. Create a new repository on GitHub (https://github.com/new)
echo 2. Run the following commands (replace YOUR_USERNAME with your GitHub username):
echo    git remote add origin https://github.com/YOUR_USERNAME/meditrack.git
echo    git branch -M main
echo    git push -u origin main
pause
