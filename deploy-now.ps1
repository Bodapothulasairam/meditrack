# Quick Deploy Script for MediTrack
# GitHub Username: Bodapothulasairam

$GitHubUsername = "Bodapothulasairam"
$RepoName = "meditrack"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Deploying MediTrack to GitHub" -ForegroundColor Cyan
Write-Host "  Username: $GitHubUsername" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if we're in the meditrack directory
if (-not (Test-Path "README.md")) {
    Write-Host "❌ Error: Please run this script from the meditrack directory" -ForegroundColor Red
    Write-Host "   Current directory: $(Get-Location)" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Please navigate to the meditrack folder first:" -ForegroundColor Yellow
    Write-Host "   cd path\to\meditrack" -ForegroundColor White
    exit 1
}

Write-Host "✅ Found MediTrack project" -ForegroundColor Green
Write-Host ""

# Initialize git if not already initialized
if (-not (Test-Path ".git")) {
    Write-Host "📦 Initializing Git repository..." -ForegroundColor Yellow
    git init
} else {
    Write-Host "ℹ️  Git repository already initialized" -ForegroundColor Blue
}

Write-Host ""

# Add all files
Write-Host "➕ Adding all files to Git..." -ForegroundColor Yellow
git add .

Write-Host ""

# Check git status
$status = git status --porcelain
if ([string]::IsNullOrWhiteSpace($status)) {
    Write-Host "ℹ️  No changes to commit" -ForegroundColor Blue
    $hasChanges = $false
} else {
    Write-Host "💾 Creating commit..." -ForegroundColor Yellow
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
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Commit created successfully!" -ForegroundColor Green
        $hasChanges = $true
    } else {
        Write-Host "⚠️  Commit may have failed or nothing to commit" -ForegroundColor Yellow
        $hasChanges = $false
    }
}

Write-Host ""

# Check if remote exists
$remoteUrl = git remote get-url origin 2>$null
if ($remoteUrl) {
    Write-Host "ℹ️  Remote 'origin' already exists: $remoteUrl" -ForegroundColor Blue
    Write-Host ""
    Write-Host "To update remote or push, run:" -ForegroundColor Yellow
    Write-Host "  git remote set-url origin https://github.com/$GitHubUsername/$RepoName.git" -ForegroundColor White
    Write-Host "  git branch -M main" -ForegroundColor White
    Write-Host "  git push -u origin main" -ForegroundColor White
} else {
    Write-Host "🔗 Setting up GitHub remote..." -ForegroundColor Yellow
    git remote add origin "https://github.com/$GitHubUsername/$RepoName.git"
    git branch -M main
    
    Write-Host ""
    Write-Host "✅ Remote configured!" -ForegroundColor Green
    Write-Host ""
    Write-Host "📤 Ready to push to GitHub!" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "IMPORTANT: First create the repository on GitHub:" -ForegroundColor Yellow
    Write-Host "1. Go to: https://github.com/new" -ForegroundColor White
    Write-Host "2. Repository name: $RepoName" -ForegroundColor White
    Write-Host "3. Description: Healthcare Patient Portal - Full-stack application" -ForegroundColor White
    Write-Host "4. Choose Public or Private" -ForegroundColor White
    Write-Host "5. DO NOT initialize with README" -ForegroundColor White
    Write-Host "6. Click 'Create repository'" -ForegroundColor White
    Write-Host ""
    Write-Host "Then run this command to push:" -ForegroundColor Yellow
    Write-Host "  git push -u origin main" -ForegroundColor White
    Write-Host ""
    
    # Ask if they want to push now
    $response = Read-Host "Have you created the repository on GitHub? (y/n)"
    if ($response -eq "y" -or $response -eq "Y") {
        Write-Host ""
        Write-Host "🚀 Pushing to GitHub..." -ForegroundColor Yellow
        git push -u origin main
        if ($LASTEXITCODE -eq 0) {
            Write-Host ""
            Write-Host "✅ Successfully deployed to GitHub!" -ForegroundColor Green
            Write-Host "   https://github.com/$GitHubUsername/$RepoName" -ForegroundColor Cyan
        } else {
            Write-Host ""
            Write-Host "❌ Push failed. Please check:" -ForegroundColor Red
            Write-Host "   - Repository exists on GitHub" -ForegroundColor Yellow
            Write-Host "   - You have push access" -ForegroundColor Yellow
            Write-Host "   - Your credentials are configured" -ForegroundColor Yellow
        }
    }
}

Write-Host ""
Write-Host "✨ Setup complete!" -ForegroundColor Green
