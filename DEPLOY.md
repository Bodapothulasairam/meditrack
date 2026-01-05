# Deploy MediTrack to GitHub

## Quick Setup

### Option 1: Using the Setup Script (Windows)

1. Open PowerShell or Command Prompt
2. Navigate to the meditrack directory
3. Run:
```bash
.\setup-git.bat
```

### Option 2: Manual Setup

Run these commands from the `meditrack` directory:

```bash
# Initialize Git
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

## Push to GitHub

1. **Create a new repository on GitHub:**
   - Go to https://github.com/new
   - Repository name: `meditrack`
   - Description: "Healthcare Patient Portal - Full-stack application with Spring Boot and React"
   - Choose Public or Private
   - **DO NOT** initialize with README, .gitignore, or license

2. **Add remote and push:**
```bash
# Replace YOUR_USERNAME with your actual GitHub username
git remote add origin https://github.com/YOUR_USERNAME/meditrack.git
git branch -M main
git push -u origin main
```

## Verify Deployment

After pushing, visit:
```
https://github.com/YOUR_USERNAME/meditrack
```

You should see all your project files there!

## Next Steps After Deployment

1. Add a GitHub Actions workflow for CI/CD (optional)
2. Set up environment variables in GitHub Secrets for production
3. Configure branch protection rules
4. Add collaborators if needed
