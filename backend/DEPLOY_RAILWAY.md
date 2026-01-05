# Deploy MediTrack Backend to Railway (Free Tier)

Railway is another great option with a free tier and easy deployment.

## Prerequisites

1. **Railway Account**: Sign up at https://railway.app (use GitHub to sign in)
2. **GitHub Account**: Your code is already on GitHub

## Step 1: Create New Project

1. Go to: https://railway.app
2. Click: "New Project"
3. Select: "Deploy from GitHub repo"
4. Choose: `Bodapothulasairam/meditrack`

## Step 2: Configure Service

1. Railway will detect your repository
2. Click on the service
3. Go to "Settings"
4. Set **Root Directory**: `backend`
5. Railway auto-detects Spring Boot!

## Step 3: Add PostgreSQL Database

1. Click: "New" → "Database" → "Add PostgreSQL"
2. Railway automatically creates the database
3. Connection variables are auto-set

## Step 4: Add MongoDB

### Option A: MongoDB Atlas (Recommended)

1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Create free cluster
3. Get connection string
4. In Railway: Go to "Variables" tab
5. Add: `MONGODB_URI` = your MongoDB Atlas connection string

### Option B: Railway MongoDB (if available)

1. Click: "New" → "Database" → "Add MongoDB" (if available)

## Step 5: Set Environment Variables

In Railway "Variables" tab, add:

| Variable | Value |
|---------|-------|
| `JWT_SECRET` | Your random secret key (min 256 bits) |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000` (update after Netlify) |
| `SPRING_PROFILES_ACTIVE` | `production` (optional) |

**Generate JWT Secret:**
- Use: https://www.random.org/strings/
- Length: 32+ characters

## Step 6: Deploy

1. Railway automatically detects changes
2. Click "Deploy" or push to GitHub
3. Railway will:
   - Build your Spring Boot app
   - Run `mvn clean install`
   - Start the application

## Step 7: Get Your Backend URL

1. After deployment, Railway provides a URL
2. Click on your service → "Settings" → "Generate Domain"
3. Your backend URL: `https://your-app.up.railway.app`

**API Base URL:**
```
https://your-app.up.railway.app/api
```

## Step 8: Update CORS

After Netlify deployment, update `CORS_ALLOWED_ORIGINS`:
```
http://localhost:3000,https://your-netlify-site.netlify.app
```

## Free Tier Limits

- **$5 credit/month** (usually enough for small apps)
- **PostgreSQL**: Included
- **Automatic deployments** from GitHub

## Advantages of Railway

- ✅ Automatic deployments
- ✅ Easy database setup
- ✅ Simple configuration
- ✅ Good free tier

## Next Steps

1. ✅ Get your Railway URL: `https://your-app.up.railway.app/api`
2. ✅ Use in Netlify: `REACT_APP_API_URL`
3. ✅ Update CORS with Netlify URL
