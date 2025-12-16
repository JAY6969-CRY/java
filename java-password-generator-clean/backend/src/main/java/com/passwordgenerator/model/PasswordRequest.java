package com.passwordgenerator.model;

public class PasswordRequest {
    private Integer length;
    private Boolean includeUppercase;
    private Boolean includeLowercase;
    private Boolean includeNumbers;
    private Boolean includeSymbols;

    public PasswordRequest() {}

    public PasswordRequest(Integer length, Boolean includeUppercase, Boolean includeLowercase, 
                          Boolean includeNumbers, Boolean includeSymbols) {
        this.length = length;
        this.includeUppercase = includeUppercase;
        this.includeLowercase = includeLowercase;
        this.includeNumbers = includeNumbers;
        this.includeSymbols = includeSymbols;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getIncludeUppercase() {
        return includeUppercase;
    }

    public void setIncludeUppercase(Boolean includeUppercase) {
        this.includeUppercase = includeUppercase;
    }

    public Boolean getIncludeLowercase() {
        return includeLowercase;
    }

    public void setIncludeLowercase(Boolean includeLowercase) {
        this.includeLowercase = includeLowercase;
    }

    public Boolean getIncludeNumbers() {
        return includeNumbers;
    }

    public void setIncludeNumbers(Boolean includeNumbers) {
        this.includeNumbers = includeNumbers;
    }

    public Boolean getIncludeSymbols() {
        return includeSymbols;
    }

    public void setIncludeSymbols(Boolean includeSymbols) {
        this.includeSymbols = includeSymbols;
    }
}
