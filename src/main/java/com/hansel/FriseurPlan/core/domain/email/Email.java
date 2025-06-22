package com.hansel.FriseurPlan.core.domain.email;

import java.util.Objects;

public class Email {
    private final String email;
    private final boolean verified;

    private Email(String email, boolean verified) {
        this.email = email;
        this.verified = verified;
    }

    public static Email create(String email, boolean verified) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        return new Email(email, verified);
    }

    public boolean validateEmailIsUnique(ValidateEmailIsUniqueService validateEmailIsUniqueService) {
        assert  validateEmailIsUniqueService != null;
        return validateEmailIsUniqueService.validate(this);
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return verified;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return verified == email1.verified && Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, verified);
    }
}
