package com.hansel.FriseurPlan.core.domain;

public class Email {
    private String email;
    private boolean verified;

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

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return verified;
    }
}
