# Git Setup Script for MediTrack
# Run this script from the meditrack directory

Write-Host "Setting up Git repository for MediTrack..." -ForegroundColor Green

# Check if we're in the meditrack directory
if (-not (Test-Path "README.md")) {
    Write-Host "Error: Please run this script from the meditrack directory" -ForegroundColor Red
    exit 1
}

# Initialize git if not already initialized
if (-not (Test-Path ".git")) {
    Write-Host "Initializing Git repository..." -ForegroundColor Yellow
    git init
}

# Add all files
Write-Host "Adding all files to Git..." -ForegroundColor Yellow
git add .

# Create initial commit
Write-Host "Creating initial commit..." -ForegroundColor Yellow
git commit -m "Initial commit: MediTrack Healthcare Patient Portal

- Full-stack healthcare patient management system
- Backend: Spring Boot 3 + Java 17 with JWT authentication
- Frontend: React 18 + Redux
- Features: Appointment booking, lab results, notifications
- Databases: PostgreSQL + MongoDB
- HIPAA-compliant secure communication"

Write-Host "`nGit repository initialized and committed successfully!" -ForegroundColor Green
Write-Host "`nNext steps:" -ForegroundColor Cyan
Write-Host "1. Create a new repository on GitHub (https://github.com/new)" -ForegroundColor White
Write-Host "2. Run the following commands (replace YOUR_USERNAME with your GitHub username):" -ForegroundColor White
Write-Host "   git remote add origin https://github.com/YOUR_USERNAME/meditrack.git" -ForegroundColor Yellow
Write-Host "   git branch -M main" -ForegroundColor Yellow
Write-Host "   git push -u origin main" -ForegroundColor Yellow
