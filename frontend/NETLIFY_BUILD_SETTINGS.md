# Netlify Build Settings - Quick Reference

## Exact Settings to Configure in Netlify Dashboard

When you add your site to Netlify, use these **exact settings**:

### Basic Settings

| Setting | Value |
|---------|-------|
| **Base directory** | `frontend` |
| **Build command** | `npm run build` |
| **Publish directory** | `frontend/build` |

### Advanced Settings

| Setting | Value |
|---------|-------|
| **Node version** | `18` (or `18.17.0` for specific version) |
| **NPM version** | `9` (or latest) |

### Environment Variables

**Required Environment Variable:**

| Variable Name | Value | Notes |
|---------------|-------|-------|
| `REACT_APP_API_URL` | `https://your-backend-url.com/api` | Replace with your actual backend URL after deployment |

## Step-by-Step Configuration

### 1. Connect Repository
- Go to https://app.netlify.com
- Click **"Add new site"** → **"Import an existing project"**
- Connect to **GitHub**
- Select repository: **`Bodapothulasairam/meditrack`**

### 2. Configure Build Settings

Click **"Show advanced"** to reveal all options:

```
Base directory:        frontend
Build command:        npm run build
Publish directory:    frontend/build
```

### 3. Add Environment Variable

Click **"New variable"**:

```
Key:   REACT_APP_API_URL
Value: https://your-backend-url.com/api
```

**Note:** You'll need to deploy your backend first. For now, you can use:
- `http://localhost:8080/api` (for testing, but won't work in production)
- Or deploy backend first, then update this value

### 4. Deploy

Click **"Deploy site"** and wait for the build to complete.

## Important Notes

1. **Backend Required**: Netlify only hosts the frontend. Your Spring Boot backend must be deployed separately.

2. **CORS Configuration**: Make sure your backend allows requests from your Netlify domain (e.g., `https://your-site.netlify.app`)

3. **Environment Variables**: The `REACT_APP_API_URL` is used at build time, so if you change it, you need to trigger a new deployment.

## Quick Deploy Checklist

- [ ] Repository connected to Netlify
- [ ] Base directory set to `frontend`
- [ ] Build command: `npm run build`
- [ ] Publish directory: `frontend/build`
- [ ] Environment variable `REACT_APP_API_URL` added
- [ ] Backend deployed (or deployment URL ready)
- [ ] CORS configured on backend
- [ ] Site deployed successfully

## After Deployment

Your site will be available at:
- **Default URL**: `https://random-name-12345.netlify.app`
- **Custom Domain**: (if you configure one)

Update the `REACT_APP_API_URL` environment variable with your backend URL and redeploy if needed.
