# Complete Netlify Deployment Guide for MediTrack

## 🎯 Overview

This guide will help you deploy the **MediTrack frontend** to Netlify. Note that Netlify only hosts static sites, so your **Spring Boot backend** needs to be deployed separately.

## 📋 Prerequisites

1. ✅ GitHub repository (already done: `Bodapothulasairam/meditrack`)
2. ✅ Netlify account (sign up at https://app.netlify.com - free)
3. ⚠️ Backend deployed (Heroku, Railway, Render, etc.)

## 🚀 Step 1: Deploy Backend First (Required)

Your backend must be deployed before the frontend can work. Choose one:

### Option A: Heroku (Recommended - Free Tier)

1. Create `backend/Procfile`:
   ```
   web: java -jar target/meditrack-backend-1.0.0.jar
   ```

2. Deploy to Heroku (see Heroku deployment guide)

3. Get your backend URL: `https://your-app.herokuapp.com`

### Option B: Railway

1. Connect GitHub repo
2. Select `backend` directory
3. Railway auto-detects Spring Boot
4. Get your backend URL

### Option C: Render

1. Create new Web Service
2. Connect GitHub repo
3. Build: `./mvnw clean install`
4. Start: `java -jar target/meditrack-backend-1.0.0.jar`

**Important:** After backend deployment, update CORS to allow your Netlify domain!

## 🌐 Step 2: Configure Netlify Build Settings

### Exact Settings:

| Setting | Value |
|---------|-------|
| **Base directory** | `frontend` |
| **Build command** | `npm run build` |
| **Publish directory** | `frontend/build` |
| **Node version** | `18` |

### Environment Variable:

| Variable | Value |
|----------|-------|
| `REACT_APP_API_URL` | `https://your-backend-url.com/api` |

## 📝 Step 3: Deploy to Netlify

### Method 1: Via Netlify Dashboard (Easiest)

1. **Go to**: https://app.netlify.com
2. **Click**: "Add new site" → "Import an existing project"
3. **Connect**: GitHub → Select `Bodapothulasairam/meditrack`
4. **Configure**:
   - Base directory: `frontend`
   - Build command: `npm run build`
   - Publish directory: `frontend/build`
5. **Add Environment Variable**:
   - Key: `REACT_APP_API_URL`
   - Value: `https://your-backend-url.com/api`
6. **Click**: "Deploy site"

### Method 2: Via Netlify CLI

```bash
# Install Netlify CLI
npm install -g netlify-cli

# Login
netlify login

# Navigate to frontend
cd frontend

# Deploy
netlify deploy --prod
```

## ⚙️ Step 4: Update Backend CORS

After you get your Netlify URL (e.g., `https://meditrack-123.netlify.app`), update your backend CORS configuration:

### In your backend deployment (Heroku/Railway/Render):

Set environment variable:
```
CORS_ALLOWED_ORIGINS=http://localhost:3000,https://meditrack-123.netlify.app
```

Or update `application.yml`:
```yaml
cors:
  allowed-origins: http://localhost:3000,https://meditrack-123.netlify.app
```

## ✅ Step 5: Verify Deployment

1. **Visit your Netlify URL**: `https://your-site.netlify.app`
2. **Test login/register**
3. **Check browser console** for any API errors
4. **Verify API calls** are going to your backend

## 🔧 Troubleshooting

### Build Fails
- ✅ Check Node version (should be 18+)
- ✅ Verify `package.json` has build script
- ✅ Check Netlify build logs

### API Calls Fail (CORS Error)
- ✅ Update backend CORS with Netlify URL
- ✅ Verify `REACT_APP_API_URL` is set correctly
- ✅ Check backend is deployed and running

### Routing Issues (404 on refresh)
- ✅ `netlify.toml` includes redirect rules
- ✅ `_redirects` file is in `public` folder

### Environment Variable Not Working
- ✅ Variable name must start with `REACT_APP_`
- ✅ Redeploy after adding/changing variables
- ✅ Variables are used at build time, not runtime

## 📁 Files Created for Netlify

- ✅ `frontend/netlify.toml` - Netlify configuration
- ✅ `frontend/public/_redirects` - React Router redirects
- ✅ `frontend/.env.example` - Environment variable template
- ✅ Updated `api.js` to use environment variables

## 🎉 After Successful Deployment

Your site will be live at:
- **Netlify URL**: `https://random-name-12345.netlify.app`
- **Custom Domain**: (if you configure one)

## 📚 Additional Resources

- Netlify Docs: https://docs.netlify.com
- React Deployment: https://create-react-app.dev/docs/deployment/#netlify
- Backend Deployment: See `HEROKU_DEPLOY.md` or `RAILWAY_DEPLOY.md`

## 🔄 Updating Your Site

Every time you push to GitHub, Netlify will automatically:
1. Detect the change
2. Build your site
3. Deploy the new version

No manual steps needed!
