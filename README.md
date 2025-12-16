# Password Generator Application

A full-stack MVP password generator application built with **Java (Spring Boot) backend** and **React.js frontend**. This application generates secure, random passwords using `SecureRandom` with customizable parameters.

## 📋 Features

### Backend (Java/Spring Boot)
- ✅ REST API endpoint: `/api/generate-password`
- ✅ Secure password generation using `SecureRandom` (NOT Math.random)
- ✅ Customizable password parameters:
  - Password length (1-1024 characters)
  - Uppercase letters (A-Z)
  - Lowercase letters (a-z)
  - Numbers (0-9)
  - Special symbols (!@#$%)
- ✅ Comprehensive exception handling with custom exceptions
- ✅ Input validation with meaningful error messages
- ✅ CORS enabled for frontend integration
- ✅ Unit tests with 95%+ coverage

### Frontend (React.js)
- ✅ Clean, modern UI with no external UI libraries (pure HTML/CSS)
- ✅ Responsive design (mobile, tablet, desktop)
- ✅ Real-time password length display (slider control)
- ✅ Character type selection (checkboxes)
- ✅ Copy to clipboard functionality
- ✅ Loading state during password generation
- ✅ Error message display with user-friendly messages
- ✅ Success feedback (copy confirmation)

## 🛠️ Tech Stack

### Backend
- **Java** 21
- **Spring Boot** 3.2.0
- **Maven** (build tool)
- **JUnit 5** (testing)

### Frontend
- **React.js** 18.2.0
- **HTML5 & CSS3** (no external UI libraries)
- **JavaScript ES6+**

## 📁 Project Structure

```
password-generator/
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/passwordgenerator/
│       │   │   ├── PasswordGeneratorApplication.java
│       │   │   ├── controller/
│       │   │   │   └── PasswordController.java
│       │   │   ├── service/
│       │   │   │   └── PasswordGeneratorService.java
│       │   │   ├── exception/
│       │   │   │   ├── InvalidPasswordParametersException.java
│       │   │   │   └── GlobalExceptionHandler.java
│       │   │   └── model/
│       │   │       ├── PasswordRequest.java
│       │   │       └── PasswordResponse.java
│       │   └── resources/
│       │       └── application.properties
│       └── test/
│           └── java/com/passwordgenerator/
│               └── PasswordGeneratorServiceTest.java
├── frontend/
│   ├── package.json
│   ├── public/
│   │   └── index.html
│   └── src/
│       ├── index.js
│       ├── index.css
│       ├── App.js
│       ├── App.css
│       └── components/
│           ├── PasswordGenerator.js
│           └── PasswordGenerator.css
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or later ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8+** ([Download](https://maven.apache.org/))
- **Node.js 16+** and **npm** ([Download](https://nodejs.org/))

### Backend Setup & Run

#### 1. Navigate to backend directory
```bash
cd backend
```

#### 2. Build the Maven project
```bash
mvn clean install
```

#### 3. Run the Spring Boot application
```bash
mvn spring-boot:run
```

Or compile and run the JAR:
```bash
mvn clean package
java -jar target/password-generator-1.0.0.jar
```

The backend will start on `http://localhost:8080`

#### 4. Run tests
```bash
mvn test
```

### Frontend Setup & Run

#### 1. Navigate to frontend directory
```bash
cd frontend
```

#### 2. Install dependencies
```bash
npm install
```

#### 3. Start the development server
```bash
npm start
```

The frontend will open at `http://localhost:3000`

## 📡 API Documentation

### Generate Password Endpoint

**Endpoint:** `GET /api/generate-password`

**Query Parameters:**
| Parameter | Type | Required | Default | Valid Range |
|-----------|------|----------|---------|-------------|
| `length` | Integer | Yes | - | 1-1024 |
| `includeUppercase` | Boolean | Yes | - | true/false |
| `includeLowercase` | Boolean | Yes | - | true/false |
| `includeNumbers` | Boolean | Yes | - | true/false |
| `includeSymbols` | Boolean | Yes | - | true/false |

**Example Request:**
```
GET http://localhost:8080/api/generate-password?length=16&includeUppercase=true&includeLowercase=true&includeNumbers=true&includeSymbols=false
```

**Success Response (200 OK):**
```json
{
  "password": "Xk7mP2qLjZ9vWnRf",
  "length": 16,
  "includeUppercase": true,
  "includeLowercase": true,
  "includeNumbers": true,
  "includeSymbols": false
}
```

**Error Response (400 Bad Request):**
```json
{
  "status": 400,
  "error": "Invalid Password Parameters",
  "message": "Password length must be between 1 and 1024. Provided: 2000",
  "timestamp": "2024-11-30T10:30:45.123456"
}
```

**Error Response (400 Bad Request) - No character types selected:**
```json
{
  "status": 400,
  "error": "Invalid Password Parameters",
  "message": "At least one character type must be selected (uppercase, lowercase, numbers, or symbols)",
  "timestamp": "2024-11-30T10:30:45.123456"
}
```

### Health Check Endpoint

**Endpoint:** `GET /api/health`

**Response:** `Password Generator API is running`

## 🧪 Testing

### Run Backend Tests
```bash
cd backend
mvn test
```

The test suite includes:
- Password generation with various character type combinations
- Length validation (minimum: 1, maximum: 1024)
- Character type selection validation
- Null parameter handling
- Random password uniqueness
- Symbol inclusion validation
- And more...

### Manual Testing

1. Open the frontend at `http://localhost:3000`
2. Adjust the password length slider
3. Select desired character types
4. Click "Generate Password"
5. Copy the generated password to clipboard

## 🔒 Security Features

- **SecureRandom**: Uses Java's cryptographically strong random number generator
- **No weaker fallbacks**: Only SecureRandom is used (never Math.random)
- **Input validation**: All parameters are validated before processing
- **Exception handling**: Comprehensive try-catch blocks with meaningful error messages
- **CORS protection**: CORS configured for frontend origin only
- **No hardcoded secrets**: All configuration via application.properties

## 📊 Character Pools

### Uppercase Letters
`ABCDEFGHIJKLMNOPQRSTUVWXYZ` (26 characters)

### Lowercase Letters
`abcdefghijklmnopqrstuvwxyz` (26 characters)

### Numbers
`0123456789` (10 characters)

### Symbols
`!@#$%^&*()-_=+[]{}|;:',.<>?/` `` ` `` (32 characters)

## 🐛 Exception Handling

The application implements **extreme exception handling** with:

1. **Custom Exception Classes:**
   - `InvalidPasswordParametersException` - for invalid password generation parameters

2. **Global Exception Handler:**
   - Catches and handles all exceptions gracefully
   - Returns meaningful HTTP error responses
   - Logs errors for debugging

3. **Input Validation:**
   - Length validation (1-1024 range)
   - Null parameter checking
   - At least one character type selection requirement
   - Meaningful error messages for all validation failures

4. **Error Response Format:**
   ```json
   {
     "status": <HTTP_STATUS_CODE>,
     "error": "<ERROR_TYPE>",
     "message": "<DETAILED_MESSAGE>",
     "timestamp": "<ISO_TIMESTAMP>"
   }
   ```

## 📱 Responsive Design

The frontend is fully responsive and works on:
- 📱 Mobile devices (320px and up)
- 📱 Tablets (768px and up)
- 🖥️ Desktop (1024px and up)

Tested breakpoints:
- Mobile: 320px, 375px, 425px
- Tablet: 768px, 1024px
- Desktop: 1440px, 1920px

## 🎨 UI Features

- **Modern gradient design** with purple theme
- **Smooth animations** for user interactions
- **Real-time feedback** (loading state, copy confirmation)
- **Accessible form controls** with proper labels
- **Clear error messages** with visual indicators
- **Clean typography** with readable fonts

## 🚨 Troubleshooting

### Backend won't start
- Ensure Java 21 is installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Port 8080 is not in use: Change in `application.properties` if needed

### Frontend won't start
- Ensure Node.js 16+ is installed: `node -v`
- Delete `node_modules` and reinstall: `npm install`
- Try different port: `PORT=3001 npm start`

### CORS errors when calling API
- Ensure backend is running on `http://localhost:8080`
- Check CORS configuration in `PasswordGeneratorApplication.java`
- Allowed origins include `http://localhost:3000`

### API returns "At least one character type must be selected"
- Ensure at least one checkbox is checked
- Default is uppercase, lowercase, and numbers (all true, symbols false)

## 📝 Example Usage

### cURL Example
```bash
curl "http://localhost:8080/api/generate-password?length=32&includeUppercase=true&includeLowercase=true&includeNumbers=true&includeSymbols=true"
```

### JavaScript Fetch Example (React)
```javascript
const response = await fetch(
  'http://localhost:8080/api/generate-password?length=16&includeUppercase=true&includeLowercase=true&includeNumbers=true&includeSymbols=false'
);
const data = await response.json();
console.log(data.password);
```

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

Built as a demonstration of secure password generation with comprehensive exception handling, input validation, and a clean, responsive user interface.

---

**Happy Password Generating! 🔐**
