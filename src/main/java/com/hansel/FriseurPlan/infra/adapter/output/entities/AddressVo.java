package com.hansel.FriseurPlan.infra.adapter.output.entities;

import com.hansel.FriseurPlan.core.domain.Address;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressVo {
    private String street;
    private int number;
    private String city;
    private String state;
    private long zipCode;

    public static AddressVo fromAddressDomain(Address address) {
        return new AddressVo(
            address.getStreet(),
            address.getNumber(),
            address.getCity(),
            address.getState(),
            address.getZipCode()
        );
    }

    public Address toAddressDomain() {
        return Address.create(street, number, city, state, zipCode);
    }
}
