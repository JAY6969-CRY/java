package com.passwordgenerator.service;

import com.passwordgenerator.exception.InvalidPasswordParametersException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordGeneratorService {

    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC_CHARS = "0123456789";
    private static final String SYMBOL_CHARS = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~";

    private static final int MIN_PASSWORD_LENGTH = 1;
    private static final int MAX_PASSWORD_LENGTH = 1024;

    private final SecureRandom secureRandom;

    public PasswordGeneratorService() {
        this.secureRandom = new SecureRandom();
    }

    public String generatePassword(Integer length, Boolean includeUppercase, Boolean includeLowercase,
                                   Boolean includeNumbers, Boolean includeSymbols) {
        try {
            // Validate input parameters
            validatePasswordParameters(length, includeUppercase, includeLowercase, includeNumbers, includeSymbols);

            // Build the character pool
            String characterPool = buildCharacterPool(includeUppercase, includeLowercase, includeNumbers, includeSymbols);

            // Generate password using SecureRandom
            return generateSecurePassword(characterPool, length);

        } catch (InvalidPasswordParametersException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPasswordParametersException(
                "An error occurred during password generation: " + e.getMessage(), e);
        }
    }

    private void validatePasswordParameters(Integer length, Boolean includeUppercase, Boolean includeLowercase,
                                           Boolean includeNumbers, Boolean includeSymbols) {
        try {
            // Check if length is provided and valid
            if (length == null) {
                throw new IllegalArgumentException("Length parameter is required and cannot be null");
            }

            if (length < MIN_PASSWORD_LENGTH || length > MAX_PASSWORD_LENGTH) {
                throw new IllegalArgumentException(
                    "Password length must be between " + MIN_PASSWORD_LENGTH + " and " + MAX_PASSWORD_LENGTH + 
                    ". Provided: " + length);
            }

            // Check if character type booleans are provided
            if (includeUppercase == null || includeLowercase == null || 
                includeNumbers == null || includeSymbols == null) {
                throw new IllegalArgumentException(
                    "All character type parameters (includeUppercase, includeLowercase, includeNumbers, includeSymbols) are required");
            }

            // Check if at least one character type is selected
            if (!includeUppercase && !includeLowercase && !includeNumbers && !includeSymbols) {
                throw new InvalidPasswordParametersException(
                    "At least one character type must be selected (uppercase, lowercase, numbers, or symbols)");
            }

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (InvalidPasswordParametersException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPasswordParametersException(
                "Validation error: " + e.getMessage(), e);
        }
    }

    private String buildCharacterPool(Boolean includeUppercase, Boolean includeLowercase,
                                     Boolean includeNumbers, Boolean includeSymbols) {
        try {
            StringBuilder pool = new StringBuilder();

            if (includeUppercase != null && includeUppercase) {
                pool.append(UPPERCASE_CHARS);
            }
            if (includeLowercase != null && includeLowercase) {
                pool.append(LOWERCASE_CHARS);
            }
            if (includeNumbers != null && includeNumbers) {
                pool.append(NUMERIC_CHARS);
            }
            if (includeSymbols != null && includeSymbols) {
                pool.append(SYMBOL_CHARS);
            }

            if (pool.length() == 0) {
                throw new InvalidPasswordParametersException(
                    "Character pool is empty. At least one character type must be selected.");
            }

            return pool.toString();

        } catch (InvalidPasswordParametersException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPasswordParametersException(
                "Error building character pool: " + e.getMessage(), e);
        }
    }

    private String generateSecurePassword(String characterPool, Integer length) {
        try {
            if (characterPool == null || characterPool.isEmpty()) {
                throw new InvalidPasswordParametersException("Character pool cannot be null or empty");
            }

            if (length == null || length <= 0) {
                throw new InvalidPasswordParametersException("Password length must be greater than 0");
            }

            StringBuilder password = new StringBuilder();
            int poolLength = characterPool.length();

            // Use SecureRandom to generate indices
            for (int i = 0; i < length; i++) {
                int randomIndex = secureRandom.nextInt(poolLength);
                password.append(characterPool.charAt(randomIndex));
            }

            return password.toString();

        } catch (InvalidPasswordParametersException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPasswordParametersException(
                "Error during password generation: " + e.getMessage(), e);
        }
    }
}
