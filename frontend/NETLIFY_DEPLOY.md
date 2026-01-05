# Deploy MediTrack Frontend to Netlify

## Important Note
**Netlify only hosts static frontend applications.** Your Spring Boot backend needs to be deployed separately to a platform like:
- Heroku (free tier available)
- Railway (free tier available)
- Render (free tier available)
- AWS/Google Cloud/Azure

## Netlify Build Settings

### Option 1: Deploy via Netlify Dashboard

1. **Go to Netlify**: https://app.netlify.com
2. **Sign up/Login** (you can use GitHub to sign in)
3. **Click "Add new site" → "Import an existing project"**
4. **Connect to GitHub** and select your `meditrack` repository
5. **Configure build settings:**
   - **Base directory**: `frontend`
   - **Build command**: `npm run build`
   - **Publish directory**: `frontend/build`
   - **Node version**: `18` (or higher)

6. **Environment Variables** (click "Show advanced" → "New variable"):
   - **Key**: `REACT_APP_API_URL`
   - **Value**: Your deployed backend URL (e.g., `https://your-backend.herokuapp.com/api`)
   - **Note**: You'll need to deploy your backend first to get this URL

7. **Click "Deploy site"**

### Option 2: Deploy via Netlify CLI

1. **Install Netlify CLI**:
   ```bash
   npm install -g netlify-cli
   ```

2. **Login to Netlify**:
   ```bash
   netlify login
   ```

3. **Navigate to frontend directory**:
   ```bash
   cd frontend
   ```

4. **Initialize and deploy**:
   ```bash
   netlify init
   netlify deploy --prod
   ```

## Build Settings Summary

| Setting | Value |
|---------|-------|
| **Base directory** | `frontend` |
| **Build command** | `npm run build` |
| **Publish directory** | `frontend/build` |
| **Node version** | `18` or higher |
| **Environment variable** | `REACT_APP_API_URL` = Your backend URL |

## Environment Variables

You **must** set the `REACT_APP_API_URL` environment variable in Netlify:

1. Go to **Site settings** → **Environment variables**
2. Add variable:
   - **Key**: `REACT_APP_API_URL`
   - **Value**: Your deployed backend API URL
   - **Scopes**: All scopes (Production, Deploy previews, Branch deploys)

## Backend Deployment Options

### Option 1: Heroku (Recommended for Free Tier)

1. Create a `Procfile` in the `backend` directory:
   ```
   web: java -jar target/meditrack-backend-1.0.0.jar
   ```

2. Deploy using Heroku CLI or GitHub integration

### Option 2: Railway

1. Connect your GitHub repository
2. Select the `backend` directory
3. Railway auto-detects Spring Boot and deploys

### Option 3: Render

1. Create a new Web Service
2. Connect your GitHub repository
3. Set build command: `./mvnw clean install`
4. Set start command: `java -jar target/meditrack-backend-1.0.0.jar`

## After Deployment

1. **Update Netlify environment variable** with your backend URL
2. **Redeploy** your Netlify site to pick up the new API URL
3. **Test** your application end-to-end

## Troubleshooting

### Build Fails
- Check Node version (should be 18+)
- Ensure `package.json` has correct build script
- Check Netlify build logs

### API Calls Fail
- Verify `REACT_APP_API_URL` is set correctly
- Check CORS settings on your backend
- Ensure backend is deployed and accessible

### Routing Issues
- The `netlify.toml` file includes redirect rules for React Router
- All routes should redirect to `index.html`

## Custom Domain (Optional)

1. Go to **Domain settings** in Netlify
2. Add your custom domain
3. Follow DNS configuration instructions
