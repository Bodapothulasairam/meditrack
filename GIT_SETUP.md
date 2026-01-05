# Git Setup Instructions for MediTrack

## Initial Git Setup

1. **Navigate to the project directory:**
```bash
cd meditrack
```

2. **Initialize Git repository:**
```bash
git init
```

3. **Add all files:**
```bash
git add .
```

4. **Create initial commit:**
```bash
git commit -m "Initial commit: MediTrack Healthcare Patient Portal"
```

## Push to GitHub

1. **Create a new repository on GitHub:**
   - Go to https://github.com/new
   - Repository name: `meditrack` (or your preferred name)
   - Make it public or private as you prefer
   - **DO NOT** initialize with README, .gitignore, or license

2. **Add remote and push:**
```bash
# Replace YOUR_USERNAME with your GitHub username
git remote add origin https://github.com/YOUR_USERNAME/meditrack.git
git branch -M main
git push -u origin main
```

## Alternative: Using SSH

If you have SSH keys set up:
```bash
git remote add origin git@github.com:YOUR_USERNAME/meditrack.git
git branch -M main
git push -u origin main
```

## Note

Before pushing, you may want to:
1. Fix the `<n>` tag in `backend/pom.xml` line 18 to `<name>` (optional, minor issue)
2. Update database credentials in `backend/src/main/resources/application.yml` (use environment variables for production)
3. Add a `.env` file for sensitive configuration (and add it to .gitignore)

## Future Updates

When you make changes:
```bash
git add .
git commit -m "Description of changes"
git push
```
