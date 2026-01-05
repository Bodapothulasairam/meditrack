# Initialize Git and Deploy MediTrack

## Step-by-Step Instructions

### Step 1: Navigate to Project Directory

Open PowerShell or Command Prompt and navigate to where the `meditrack` folder is located.

### Step 2: Initialize Git and Commit

Run these commands:

```powershell
# Navigate to meditrack directory
cd meditrack

# Initialize Git repository
git init

# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: MediTrack Healthcare Patient Portal

- Full-stack healthcare patient management system
- Backend: Spring Boot 3 + Java 17 with JWT authentication  
- Frontend: React 18 + Redux
- Features: Appointment booking, lab results, notifications
- Databases: PostgreSQL + MongoDB
- HIPAA-compliant secure communication"
```

### Step 3: Create GitHub Repository

1. Go to https://github.com/new
2. Repository name: `meditrack`
3. Description: "Healthcare Patient Portal - Full-stack application with Spring Boot and React"
4. Choose Public or Private
5. **DO NOT** check "Initialize this repository with a README"
6. Click "Create repository"

### Step 4: Push to GitHub

Replace `YOUR_USERNAME` with your actual GitHub username:

```powershell
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/meditrack.git

# Rename branch to main
git branch -M main

# Push to GitHub
git push -u origin main
```

### Step 5: Verify

Visit `https://github.com/YOUR_USERNAME/meditrack` to see your deployed project!

## Alternative: One-Line Setup Script

You can also run the provided `setup-git.bat` (Windows) or `setup-git.ps1` (PowerShell) script from the meditrack directory.
