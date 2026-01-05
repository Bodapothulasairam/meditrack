# Deploy MediTrack Backend to Heroku (Free Tier)

## Prerequisites

1. **Heroku Account**: Sign up at https://signup.heroku.com (free)
2. **Heroku CLI**: Download from https://devcenter.heroku.com/articles/heroku-cli
3. **Git**: Already installed
4. **PostgreSQL Database**: We'll add it via Heroku addon (free tier available)
5. **MongoDB**: We'll use MongoDB Atlas (free tier) or Heroku addon

## Step 1: Install Heroku CLI

1. Download from: https://devcenter.heroku.com/articles/heroku-cli
2. Install and verify:
   ```bash
   heroku --version
   ```

## Step 2: Login to Heroku

```bash
heroku login
```

This will open a browser window for authentication.

## Step 3: Create Heroku App

```bash
# Navigate to backend directory
cd backend

# Create Heroku app
heroku create meditrack-backend

# Or with a custom name (must be unique):
heroku create your-unique-name-meditrack
```

**Note:** Your app name must be globally unique. If `meditrack-backend` is taken, use a different name.

## Step 4: Add PostgreSQL Database (Free Tier)

```bash
heroku addons:create heroku-postgresql:mini
```

This adds a free PostgreSQL database. Heroku will automatically set `JDBC_DATABASE_URL`, `JDBC_DATABASE_USERNAME`, and `JDBC_DATABASE_PASSWORD` environment variables.

## Step 5: Add MongoDB

### Option A: MongoDB Atlas (Recommended - Free)

1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Create a free cluster
3. Get connection string (looks like: `mongodb+srv://user:pass@cluster.mongodb.net/meditrack`)
4. Set in Heroku:
   ```bash
   heroku config:set MONGODB_URI="your-mongodb-atlas-connection-string"
   ```

### Option B: Heroku MongoDB Addon

```bash
heroku addons:create mongolab:sandbox
```

## Step 6: Set Environment Variables

```bash
# Set JWT Secret (generate a random string)
heroku config:set JWT_SECRET="your-super-secret-jwt-key-min-256-bits-long-for-production"

# Set CORS (update with your Netlify URL after frontend deployment)
heroku config:set CORS_ALLOWED_ORIGINS="http://localhost:3000"

# For production profile (optional)
heroku config:set SPRING_PROFILES_ACTIVE="heroku"
```

**Generate JWT Secret:**
```bash
# On Windows PowerShell:
-join ((65..90) + (97..122) + (48..57) | Get-Random -Count 32 | ForEach-Object {[char]$_})

# Or use an online generator: https://www.random.org/strings/
```

## Step 7: Build and Deploy

```bash
# Make sure you're in the backend directory
cd backend

# Build the project
mvnw.cmd clean install -DskipTests

# Or if you have Maven installed:
mvn clean install -DskipTests

# Deploy to Heroku
git init  # If not already initialized
git add .
git commit -m "Deploy to Heroku"
heroku git:remote -a your-app-name
git push heroku main

# Or if your branch is master:
git push heroku master
```

## Step 8: Verify Deployment

```bash
# Check app status
heroku ps

# View logs
heroku logs --tail

# Test the health endpoint
heroku open
# Then visit: https://your-app-name.herokuapp.com/api/health
```

## Step 9: Get Your Backend URL

Your backend URL will be:
```
https://your-app-name.herokuapp.com
```

**API Base URL:**
```
https://your-app-name.herokuapp.com/api
```

## Step 10: Update CORS After Netlify Deployment

After you deploy your frontend to Netlify, update CORS:

```bash
heroku config:set CORS_ALLOWED_ORIGINS="http://localhost:3000,https://your-netlify-site.netlify.app"
```

## Troubleshooting

### Build Fails
- Check Heroku logs: `heroku logs --tail`
- Verify Java version in `system.properties`
- Ensure `Procfile` exists and is correct

### Database Connection Issues
- Verify PostgreSQL addon is attached: `heroku addons`
- Check database URL: `heroku config:get JDBC_DATABASE_URL`

### Application Crashes
- Check logs: `heroku logs --tail`
- Verify all environment variables are set: `heroku config`
- Test locally with same environment variables

### Port Issues
- Heroku sets `PORT` automatically - don't hardcode it
- Your `application.yml` should use `${PORT:8080}`

## Free Tier Limits

- **Dyno Hours**: 550 hours/month (enough for 1 app 24/7)
- **PostgreSQL**: 10,000 rows (free tier)
- **MongoDB Atlas**: 512MB storage (free tier)

## Useful Commands

```bash
# View all config vars
heroku config

# View logs
heroku logs --tail

# Open app in browser
heroku open

# Run commands on dyno
heroku run bash

# Restart app
heroku restart

# Scale dyno (free tier: web=1)
heroku ps:scale web=1
```

## Next Steps

After backend is deployed:
1. ✅ Get your backend URL: `https://your-app.herokuapp.com/api`
2. ✅ Use this URL in Netlify environment variable: `REACT_APP_API_URL`
3. ✅ Update CORS with your Netlify URL
