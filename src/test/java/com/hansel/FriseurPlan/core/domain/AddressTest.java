package com.hansel.FriseurPlan.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {


    @Test
    void shouldCreateAddressWithValidData() {
        Address address = Address.create("123 Main St", 1, "Springfield", "SP", 14170080L);

        assertNotNull(address);
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Springfield", address.getCity());
        assertEquals("SP", address.getState());
        assertEquals(14170080L, address.getZipCode());
    }

    @Test
    void shouldThrowExceptionWhenCreatingAddressWithInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> Address.create(null, 1, "Springfield", "SP", 14170080L));
        assertThrows(IllegalArgumentException.class, () -> Address.create("123 Main St", 0, "Springfield", "SP", 14170080L));
        assertThrows(IllegalArgumentException.class, () -> Address.create("123 Main St", 1, null, "SP", 14170080L));
        assertThrows(IllegalArgumentException.class, () -> Address.create("123 Main St", 1, "Springfield", null, 14170080L));
        assertThrows(IllegalArgumentException.class, () -> Address.create("123 Main St", 1, "Springfield", "SP", null));
    }

}