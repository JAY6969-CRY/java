package com.passwordgenerator;

import com.passwordgenerator.exception.InvalidPasswordParametersException;
import com.passwordgenerator.service.PasswordGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Password Generator Service Tests")
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService passwordGeneratorService;

    @BeforeEach
    public void setUp() {
        passwordGeneratorService = new PasswordGeneratorService();
    }

    @Test
    @DisplayName("Should generate password with all character types")
    public void testGeneratePasswordWithAllCharTypes() {
        String password = passwordGeneratorService.generatePassword(
                16, true, true, true, true);

        assertNotNull(password);
        assertEquals(16, password.length());
        assertFalse(password.isEmpty());
    }

    @Test
    @DisplayName("Should generate password with only uppercase and lowercase")
    public void testGeneratePasswordWithOnlyLetters() {
        String password = passwordGeneratorService.generatePassword(
                20, true, true, false, false);

        assertNotNull(password);
        assertEquals(20, password.length());
        assertTrue(password.matches("[A-Za-z]{20}"));
    }

    @Test
    @DisplayName("Should generate password with only numbers")
    public void testGeneratePasswordWithOnlyNumbers() {
        String password = passwordGeneratorService.generatePassword(
                10, false, false, true, false);

        assertNotNull(password);
        assertEquals(10, password.length());
        assertTrue(password.matches("[0-9]{10}"));
    }

    @Test
    @DisplayName("Should generate password with only lowercase and numbers")
    public void testGeneratePasswordWithLowercaseAndNumbers() {
        String password = passwordGeneratorService.generatePassword(
                15, false, true, true, false);

        assertNotNull(password);
        assertEquals(15, password.length());
        assertTrue(password.matches("[a-z0-9]{15}"));
    }

    @Test
    @DisplayName("Should generate different passwords on consecutive calls")
    public void testGenerateDifferentPasswords() {
        String password1 = passwordGeneratorService.generatePassword(
                32, true, true, true, true);
        String password2 = passwordGeneratorService.generatePassword(
                32, true, true, true, true);

        assertNotNull(password1);
        assertNotNull(password2);
        assertNotEquals(password1, password2);
    }

    @Test
    @DisplayName("Should throw exception when no character types are selected")
    public void testThrowExceptionWhenNoCharTypesSelected() {
        assertThrows(InvalidPasswordParametersException.class, () -> {
            passwordGeneratorService.generatePassword(16, false, false, false, false);
        });
    }

    @Test
    @DisplayName("Should throw exception when length is null")
    public void testThrowExceptionWhenLengthIsNull() {
        assertThrows(Exception.class, () -> {
            passwordGeneratorService.generatePassword(null, true, true, true, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when length is too small")
    public void testThrowExceptionWhenLengthTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> {
            passwordGeneratorService.generatePassword(0, true, true, true, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when length is too large")
    public void testThrowExceptionWhenLengthTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> {
            passwordGeneratorService.generatePassword(2000, true, true, true, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when includeUppercase is null")
    public void testThrowExceptionWhenIncludeUppercaseIsNull() {
        assertThrows(Exception.class, () -> {
            passwordGeneratorService.generatePassword(16, null, true, true, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when includeLowercase is null")
    public void testThrowExceptionWhenIncludeLowercaseIsNull() {
        assertThrows(Exception.class, () -> {
            passwordGeneratorService.generatePassword(16, true, null, true, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when includeNumbers is null")
    public void testThrowExceptionWhenIncludeNumbersIsNull() {
        assertThrows(Exception.class, () -> {
            passwordGeneratorService.generatePassword(16, true, true, null, true);
        });
    }

    @Test
    @DisplayName("Should throw exception when includeSymbols is null")
    public void testThrowExceptionWhenIncludeSymbolsIsNull() {
        assertThrows(Exception.class, () -> {
            passwordGeneratorService.generatePassword(16, true, true, true, null);
        });
    }

    @Test
    @DisplayName("Should generate minimum length password (length = 1)")
    public void testGenerateMinimumLengthPassword() {
        String password = passwordGeneratorService.generatePassword(1, true, true, true, true);

        assertNotNull(password);
        assertEquals(1, password.length());
    }

    @Test
    @DisplayName("Should generate maximum length password (length = 1024)")
    public void testGenerateMaximumLengthPassword() {
        String password = passwordGeneratorService.generatePassword(1024, true, true, true, true);

        assertNotNull(password);
        assertEquals(1024, password.length());
    }

    @Test
    @DisplayName("Should consistently generate correct length passwords")
    public void testGenerateConsistentLengths() {
        int[] lengths = {5, 10, 20, 50, 100, 256};

        for (int length : lengths) {
            String password = passwordGeneratorService.generatePassword(
                    length, true, true, true, true);
            assertEquals(length, password.length(), 
                "Password length should be " + length + " but got " + password.length());
        }
    }

    @Test
    @DisplayName("Should generate password with symbols included")
    public void testGeneratePasswordWithSymbols() {
        String password = passwordGeneratorService.generatePassword(
                100, false, false, false, true);

        assertNotNull(password);
        assertEquals(100, password.length());
        // Check that at least one symbol is present
        String symbolRegex = "[!@#$%^&*()\\-_=+\\[\\]{}|;:',.<>?/`~]{100}";
        assertTrue(password.matches(symbolRegex));
    }

    @Test
    @DisplayName("Should generate password with only uppercase")
    public void testGeneratePasswordWithOnlyUppercase() {
        String password = passwordGeneratorService.generatePassword(
                20, true, false, false, false);

        assertNotNull(password);
        assertEquals(20, password.length());
        assertTrue(password.matches("[A-Z]{20}"));
    }
}
