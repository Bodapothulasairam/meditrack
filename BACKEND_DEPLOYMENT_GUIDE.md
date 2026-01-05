# Backend Deployment Guide - Choose Your Platform

Since you don't have a backend URL yet, you need to deploy your Spring Boot backend first. Here are your **free options**:

## 🎯 Quick Comparison

| Platform | Free Tier | Ease | Best For |
|----------|-----------|------|----------|
| **Heroku** | ✅ 550 hours/month | ⭐⭐⭐⭐ | Most popular, well-documented |
| **Railway** | ✅ $5 credit/month | ⭐⭐⭐⭐⭐ | Easiest setup, auto-deploy |
| **Render** | ✅ Free (spins down) | ⭐⭐⭐ | Good alternative |

## 🚀 Recommended: Railway (Easiest)

**Why Railway?**
- ✅ Easiest setup (just connect GitHub)
- ✅ Auto-detects Spring Boot
- ✅ Automatic deployments
- ✅ Free tier with $5 credit/month

**Quick Start:**
1. Go to: https://railway.app
2. Sign up with GitHub
3. New Project → Deploy from GitHub
4. Select `Bodapothulasairam/meditrack`
5. Set Root Directory: `backend`
6. Add PostgreSQL database
7. Add MongoDB (use MongoDB Atlas free tier)
8. Set environment variables
9. Deploy!

**See detailed guide:** `backend/DEPLOY_RAILWAY.md`

## 🏆 Alternative: Heroku (Most Popular)

**Why Heroku?**
- ✅ Most documentation available
- ✅ 550 free hours/month
- ✅ Well-established platform

**Quick Start:**
1. Install Heroku CLI
2. `heroku login`
3. `cd backend`
4. `heroku create your-app-name`
5. `heroku addons:create heroku-postgresql:mini`
6. Set environment variables
7. `git push heroku main`

**See detailed guide:** `backend/DEPLOY_HEROKU.md`

## 💡 Alternative: Render

**Why Render?**
- ✅ Free tier available
- ✅ Simple interface
- ⚠️ Spins down after inactivity (free tier)

**See detailed guide:** `backend/DEPLOY_RENDER.md`

## 📋 What You'll Need

### 1. PostgreSQL Database
- **Heroku**: Add `heroku-postgresql:mini` addon
- **Railway**: Add PostgreSQL database service
- **Render**: Create PostgreSQL database

### 2. MongoDB
- **All platforms**: Use **MongoDB Atlas** (free tier)
  - Sign up: https://www.mongodb.com/cloud/atlas/register
  - Create free cluster
  - Get connection string

### 3. Environment Variables

You'll need to set these:

| Variable | Description | Example |
|----------|-------------|---------|
| `JWT_SECRET` | Secret key for JWT tokens | Random 32+ character string |
| `CORS_ALLOWED_ORIGINS` | Allowed frontend URLs | `http://localhost:3000` |
| `MONGODB_URI` | MongoDB connection string | `mongodb+srv://...` |
| Database vars | Auto-set by platform | (automatic) |

## 🎯 Step-by-Step: Railway (Recommended)

### Step 1: Sign Up
- Go to: https://railway.app
- Click "Start a New Project"
- Sign in with GitHub

### Step 2: Deploy Backend
1. Click "New Project"
2. Select "Deploy from GitHub repo"
3. Choose `Bodapothulasairam/meditrack`
4. Railway will create a service

### Step 3: Configure
1. Click on the service
2. Go to "Settings"
3. Set **Root Directory**: `backend`
4. Railway auto-detects Spring Boot!

### Step 4: Add Database
1. Click "New" → "Database" → "Add PostgreSQL"
2. Railway auto-sets connection variables

### Step 5: Add MongoDB
1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Create account and free cluster
3. Get connection string
4. In Railway: "Variables" tab → Add `MONGODB_URI`

### Step 6: Set Variables
In Railway "Variables" tab:

```
JWT_SECRET=your-random-secret-key-32-chars-minimum
CORS_ALLOWED_ORIGINS=http://localhost:3000
MONGODB_URI=mongodb+srv://user:pass@cluster.mongodb.net/meditrack
```

### Step 7: Deploy
- Railway automatically deploys
- Wait for build to complete
- Get your URL: `https://your-app.up.railway.app`

### Step 8: Get Your Backend URL
Your API base URL will be:
```
https://your-app.up.railway.app/api
```

## ✅ After Backend is Deployed

1. **Copy your backend URL**: `https://your-app.up.railway.app/api`
2. **Use in Netlify**: Set `REACT_APP_API_URL` environment variable
3. **Update CORS**: Add your Netlify URL to `CORS_ALLOWED_ORIGINS`

## 🆘 Need Help?

- **Railway Docs**: https://docs.railway.app
- **Heroku Docs**: https://devcenter.heroku.com
- **Render Docs**: https://render.com/docs

## 📝 Quick Checklist

- [ ] Choose platform (Railway recommended)
- [ ] Sign up and connect GitHub
- [ ] Deploy backend
- [ ] Add PostgreSQL database
- [ ] Set up MongoDB Atlas
- [ ] Configure environment variables
- [ ] Get backend URL
- [ ] Test backend health endpoint
- [ ] Use URL in Netlify deployment

Once you have your backend URL, you can proceed with Netlify frontend deployment!
