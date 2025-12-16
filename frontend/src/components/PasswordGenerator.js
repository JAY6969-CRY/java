import React, { useState } from 'react';
import './PasswordGenerator.css';

const PasswordGenerator = () => {
  const [length, setLength] = useState(16);
  const [includeUppercase, setIncludeUppercase] = useState(true);
  const [includeLowercase, setIncludeLowercase] = useState(true);
  const [includeNumbers, setIncludeNumbers] = useState(true);
  const [includeSymbols, setIncludeSymbols] = useState(false);
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [copied, setCopied] = useState(false);

  const handleGeneratePassword = async () => {
    try {
      setLoading(true);
      setError('');
      setCopied(false);

      // Validate that at least one character type is selected
      if (!includeUppercase && !includeLowercase && !includeNumbers && !includeSymbols) {
        throw new Error('Please select at least one character type');
      }

      // Validate length
      if (length < 1 || length > 1024) {
        throw new Error('Password length must be between 1 and 1024');
      }

      // Construct API URL with query parameters
      const params = new URLSearchParams({
        length: length,
        includeUppercase: includeUppercase,
        includeLowercase: includeLowercase,
        includeNumbers: includeNumbers,
        includeSymbols: includeSymbols
      });

      const apiUrl = (process.env.REACT_APP_API_URL || 'http://localhost:8080').replace(/\/$/, '');
      const response = await fetch(`${apiUrl}/api/generate-password?${params}`);

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to generate password');
      }

      const data = await response.json();
      setPassword(data.password);
    } catch (err) {
      setError(err.message || 'An error occurred while generating the password');
      setPassword('');
    } finally {
      setLoading(false);
    }
  };

  const handleCopyToClipboard = () => {
    try {
      if (!password) {
        setError('No password to copy');
        return;
      }

      navigator.clipboard.writeText(password);
      setCopied(true);

      // Reset copied status after 2 seconds
      setTimeout(() => {
        setCopied(false);
      }, 2000);
    } catch (err) {
      setError('Failed to copy to clipboard: ' + err.message);
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h1 className="title">Secure Password Generator</h1>
        <p className="subtitle">Generate strong, random passwords with custom parameters</p>

        <div className="form-group">
          <label htmlFor="length" className="label">
            Password Length: <span className="length-value">{length}</span>
          </label>
          <input
            type="range"
            id="length"
            min="1"
            max="1024"
            value={length}
            onChange={(e) => setLength(parseInt(e.target.value))}
            className="slider"
            disabled={loading}
          />
          <div className="length-info">
            <small>Minimum: 1 | Maximum: 1024</small>
          </div>
        </div>

        <div className="form-group">
          <label className="label">Character Types:</label>
          <div className="checkboxes">
            <div className="checkbox-group">
              <input
                type="checkbox"
                id="uppercase"
                checked={includeUppercase}
                onChange={(e) => setIncludeUppercase(e.target.checked)}
                disabled={loading}
                className="checkbox-input"
              />
              <label htmlFor="uppercase" className="checkbox-label">
                Uppercase (A-Z)
              </label>
            </div>

            <div className="checkbox-group">
              <input
                type="checkbox"
                id="lowercase"
                checked={includeLowercase}
                onChange={(e) => setIncludeLowercase(e.target.checked)}
                disabled={loading}
                className="checkbox-input"
              />
              <label htmlFor="lowercase" className="checkbox-label">
                Lowercase (a-z)
              </label>
            </div>

            <div className="checkbox-group">
              <input
                type="checkbox"
                id="numbers"
                checked={includeNumbers}
                onChange={(e) => setIncludeNumbers(e.target.checked)}
                disabled={loading}
                className="checkbox-input"
              />
              <label htmlFor="numbers" className="checkbox-label">
                Numbers (0-9)
              </label>
            </div>

            <div className="checkbox-group">
              <input
                type="checkbox"
                id="symbols"
                checked={includeSymbols}
                onChange={(e) => setIncludeSymbols(e.target.checked)}
                disabled={loading}
                className="checkbox-input"
              />
              <label htmlFor="symbols" className="checkbox-label">
                Symbols (!@#$%)
              </label>
            </div>
          </div>
        </div>

        <button
          onClick={handleGeneratePassword}
          disabled={loading}
          className="btn btn-primary"
        >
          {loading ? 'Generating...' : 'Generate Password'}
        </button>

        {error && <div className="error-message">{error}</div>}

        {password && (
          <div className="password-section">
            <div className="password-display">
              <input
                type="text"
                value={password}
                readOnly
                className="password-input"
              />
              <button
                onClick={handleCopyToClipboard}
                className="btn btn-secondary"
                title="Copy to clipboard"
              >
                {copied ? '✓ Copied!' : 'Copy'}
              </button>
            </div>
            <p className="password-length-info">
              Length: {password.length} characters
            </p>
          </div>
        )}
      </div>

      <footer className="footer">
        <p>Password Generator v1.0 | Secure password generation using SecureRandom</p>
      </footer>
    </div>
  );
};

export default PasswordGenerator;
