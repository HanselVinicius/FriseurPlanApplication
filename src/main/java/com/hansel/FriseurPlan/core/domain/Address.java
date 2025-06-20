package com.hansel.FriseurPlan.core.domain;

import java.util.Objects;

public class Address {
    private String street;
    private int number;
    private String city;
    private String state;
    private Long zipCode;


    private Address(String street, int number, String city, String state, Long zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public static Address create(String street, int number, String city, String state, Long zipCode) {
        if (street == null || city == null || state == null || zipCode == null || number <= 0) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        return new Address(street, number, city, state, zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, state, zipCode);
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Long getZipCode() {
        return zipCode;
    }
}
