package com.passwordgenerator.controller;

import com.passwordgenerator.model.PasswordRequest;
import com.passwordgenerator.model.PasswordResponse;
import com.passwordgenerator.service.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    /**
     * Generates a password based on provided parameters
     * 
     * @param length Password length (1-1024)
     * @param includeUppercase Include uppercase letters (A-Z)
     * @param includeLowercase Include lowercase letters (a-z)
     * @param includeNumbers Include numeric digits (0-9)
     * @param includeSymbols Include special symbols
     * @return PasswordResponse containing the generated password
     */
    @GetMapping("/generate-password")
    public ResponseEntity<PasswordResponse> generatePassword(
            @RequestParam(value = "length") Integer length,
            @RequestParam(value = "includeUppercase", defaultValue = "false") Boolean includeUppercase,
            @RequestParam(value = "includeLowercase", defaultValue = "false") Boolean includeLowercase,
            @RequestParam(value = "includeNumbers", defaultValue = "false") Boolean includeNumbers,
            @RequestParam(value = "includeSymbols", defaultValue = "false") Boolean includeSymbols) {

        try {
            // Generate the password using the service
            String generatedPassword = passwordGeneratorService.generatePassword(
                    length, includeUppercase, includeLowercase, includeNumbers, includeSymbols);

            // Create response object
            PasswordResponse response = new PasswordResponse(
                    generatedPassword, length, includeUppercase, includeLowercase, includeNumbers, includeSymbols);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // Exception handling is delegated to GlobalExceptionHandler
            throw e;
        }
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Password Generator API is running", HttpStatus.OK);
    }
}
