# Deploy MediTrack Backend to Render (Free Tier)

Render offers a free tier with automatic deployments.

## Prerequisites

1. **Render Account**: Sign up at https://render.com (use GitHub)
2. **GitHub Account**: Your code is already on GitHub

## Step 1: Create New Web Service

1. Go to: https://dashboard.render.com
2. Click: "New +" → "Web Service"
3. Connect: Your GitHub account
4. Select: `Bodapothulasairam/meditrack` repository

## Step 2: Configure Service

Fill in the settings:

| Setting | Value |
|---------|-------|
| **Name** | `meditrack-backend` |
| **Region** | Choose closest to you |
| **Branch** | `main` |
| **Root Directory** | `backend` |
| **Runtime** | `Java` |
| **Build Command** | `./mvnw clean install -DskipTests` |
| **Start Command** | `java -jar target/meditrack-backend-1.0.0.jar` |

## Step 3: Add PostgreSQL Database

1. Click: "New +" → "PostgreSQL"
2. Name: `meditrack-db`
3. Plan: **Free** (if available)
4. Note the connection details

## Step 4: Add MongoDB

Use MongoDB Atlas (free tier):
1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Create free cluster
3. Get connection string

## Step 5: Set Environment Variables

In your Web Service → "Environment" tab, add:

| Variable | Value |
|---------|-------|
| `JDBC_DATABASE_URL` | From PostgreSQL service (Render provides this) |
| `JDBC_DATABASE_USERNAME` | From PostgreSQL service |
| `JDBC_DATABASE_PASSWORD` | From PostgreSQL service |
| `MONGODB_URI` | Your MongoDB Atlas connection string |
| `JWT_SECRET` | Random secret (32+ characters) |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000` |
| `PORT` | `10000` (Render's default, but auto-set) |

**Link PostgreSQL:**
- In your Web Service settings
- Go to "Environment" tab
- Click "Link Resource" → Select your PostgreSQL database
- Render auto-sets `JDBC_DATABASE_URL`, etc.

## Step 6: Deploy

1. Click: "Create Web Service"
2. Render will:
   - Build your application
   - Deploy it
   - Provide a URL

## Step 7: Get Your Backend URL

After deployment, Render provides:
```
https://meditrack-backend.onrender.com
```

**API Base URL:**
```
https://meditrack-backend.onrender.com/api
```

## Step 8: Update CORS

After Netlify deployment:
```
CORS_ALLOWED_ORIGINS=http://localhost:3000,https://your-netlify-site.netlify.app
```

## Free Tier Notes

- ⚠️ Free tier services **spin down after 15 minutes** of inactivity
- First request after spin-down takes ~30 seconds
- Consider upgrading for production use

## Troubleshooting

### Build Fails
- Check build logs in Render dashboard
- Verify Maven wrapper exists
- Ensure Java 17 is specified

### Service Crashes
- Check logs in Render dashboard
- Verify all environment variables
- Test database connections

## Next Steps

1. ✅ Get Render URL: `https://your-app.onrender.com/api`
2. ✅ Use in Netlify: `REACT_APP_API_URL`
3. ✅ Update CORS
