# MediTrack Git Deployment Script
# This script initializes Git, commits all files, and prepares for GitHub deployment

param(
    [string]$GitHubUsername = ""
)

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  MediTrack Git Deployment Setup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if we're in the meditrack directory
if (-not (Test-Path "README.md")) {
    Write-Host "❌ Error: Please run this script from the meditrack directory" -ForegroundColor Red
    Write-Host "   Current directory: $(Get-Location)" -ForegroundColor Yellow
    exit 1
}

Write-Host "✅ Found MediTrack project files" -ForegroundColor Green
Write-Host ""

# Initialize git if not already initialized
if (-not (Test-Path ".git")) {
    Write-Host "📦 Initializing Git repository..." -ForegroundColor Yellow
    git init
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Failed to initialize Git repository" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "ℹ️  Git repository already initialized" -ForegroundColor Blue
}

Write-Host ""

# Add all files
Write-Host "➕ Adding all files to Git..." -ForegroundColor Yellow
git add .
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Failed to add files to Git" -ForegroundColor Red
    exit 1
}

Write-Host ""

# Check if there are changes to commit
$status = git status --porcelain
if ([string]::IsNullOrWhiteSpace($status)) {
    Write-Host "ℹ️  No changes to commit (repository is up to date)" -ForegroundColor Blue
} else {
    # Create initial commit
    Write-Host "💾 Creating initial commit..." -ForegroundColor Yellow
    $commitMessage = @"
Initial commit: MediTrack Healthcare Patient Portal

- Full-stack healthcare patient management system
- Backend: Spring Boot 3 + Java 17 with JWT authentication
- Frontend: React 18 + Redux
- Features: Appointment booking, lab results, notifications
- Databases: PostgreSQL + MongoDB
- HIPAA-compliant secure communication
"@
    
    git commit -m $commitMessage
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Failed to create commit" -ForegroundColor Red
        exit 1
    }
    Write-Host "✅ Commit created successfully!" -ForegroundColor Green
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Next Steps to Deploy to GitHub" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if remote already exists
$remoteExists = git remote get-url origin 2>$null
if ($remoteExists) {
    Write-Host "ℹ️  Remote 'origin' already exists: $remoteExists" -ForegroundColor Blue
    Write-Host ""
    Write-Host "To push to GitHub, run:" -ForegroundColor Yellow
    Write-Host "  git push -u origin main" -ForegroundColor White
} else {
    if ($GitHubUsername) {
        Write-Host "🔗 Adding GitHub remote..." -ForegroundColor Yellow
        git remote add origin "https://github.com/$GitHubUsername/meditrack.git"
        git branch -M main
        Write-Host ""
        Write-Host "✅ Remote added! Now run:" -ForegroundColor Green
        Write-Host "  git push -u origin main" -ForegroundColor White
    } else {
        Write-Host "1. Create a new repository on GitHub:" -ForegroundColor Yellow
        Write-Host "   https://github.com/new" -ForegroundColor White
        Write-Host "   Repository name: meditrack" -ForegroundColor White
        Write-Host "   DO NOT initialize with README" -ForegroundColor White
        Write-Host ""
        Write-Host "2. Add remote and push (replace YOUR_USERNAME):" -ForegroundColor Yellow
        Write-Host "   git remote add origin https://github.com/YOUR_USERNAME/meditrack.git" -ForegroundColor White
        Write-Host "   git branch -M main" -ForegroundColor White
        Write-Host "   git push -u origin main" -ForegroundColor White
        Write-Host ""
        Write-Host "   Or run this script again with your GitHub username:" -ForegroundColor Cyan
        Write-Host "   .\deploy-to-github.ps1 -GitHubUsername YOUR_USERNAME" -ForegroundColor White
    }
}

Write-Host ""
Write-Host "✨ Setup complete!" -ForegroundColor Green
