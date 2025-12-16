# Run Backend and Frontend

## Terminal 1 - Run Backend (Java)

```powershell
$javaPath = 'C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot\bin\java.exe'
$jarPath = 'e:\java password generator\backend\target\password-generator-1.0.0.jar'
& $javaPath -jar $jarPath
```

Backend will run on: **http://localhost:8080/api**

---

## Terminal 2 - Run Frontend (React)

```powershell
$env:PATH = "C:\Program Files\nodejs;$env:PATH"
$env:BROWSER = "none"
cd "e:\java password generator\frontend"
& 'C:\Program Files\nodejs\npm.cmd' start
```

Frontend will run on: **http://localhost:3000**

---

## What Each Command Does

**Backend Command:**
- Sets JAVA_HOME to the Eclipse Adoptium JDK
- Runs the compiled JAR file
- Starts Spring Boot on port 8080

**Frontend Command:**
- Adds Node.js to PATH
- Disables auto-opening browser (BROWSER=none)
- Navigates to frontend folder
- Starts React development server on port 3000

---

## When Running

✅ Backend logs: You'll see Spring Boot startup messages
✅ Frontend: Browser may auto-open, or open http://localhost:3000 manually

---

## Testing

Once both are running:

1. Go to: http://localhost:3000
2. Generate a password by:
   - Entering password length (slider)
   - Checking character type options
   - Clicking "Generate Password"
3. Copy password to clipboard

---

## API Testing (curl)

```powershell
$url = "http://localhost:8080/api/generate-password?length=16&includeUppercase=true&includeLowercase=true&includeNumbers=true&includeSymbols=false"
Invoke-RestMethod $url
```

Should return:
```json
{
  "password": "XkMp2qLjZ9vWnRf",
  "length": 16,
  "includeUppercase": true,
  "includeLowercase": true,
  "includeNumbers": true,
  "includeSymbols": false
}
```

---

## Troubleshooting

**Backend won't start:**
- Check if port 8080 is in use: `netstat -ano | findstr :8080`
- Kill process if needed: `taskkill /PID <PID> /F`

**Frontend won't start:**
- Make sure you're in the frontend directory
- Make sure npm installed successfully (node_modules folder exists)
- Try: `& 'C:\Program Files\nodejs\npm.cmd' install` again

**Can't connect between frontend and backend:**
- Check backend is running: http://localhost:8080/api/health
- Check browser console (F12) for CORS errors
- Make sure both are running

---

Done! Everything is ready to go! 🚀
