package com.hansel.FriseurPlan.core.domain;


public class PhoneNumber {

    private final String number;

    private PhoneNumber(String number) {
        this.number = number;
    }

    public static PhoneNumber create(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Both number and country code must be provided");
        }

        String cleanNumber = number.replaceAll("\\D", "");

        if (!cleanNumber.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

        return new PhoneNumber(cleanNumber);
    }

    public String getNumber() {
        return number;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return this.number;
    }
}
