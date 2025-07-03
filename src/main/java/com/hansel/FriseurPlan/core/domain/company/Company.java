package com.hansel.FriseurPlan.core.domain.company;

import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final Long id;
    private final String name;
    private final String documentNumber;
    private final List<Hairdresser> hairdressers;


    public static Company createCompany(Long id, String name, String documentNumber){
        return new Company(id,name,documentNumber,new ArrayList<>());
    }

    public void boundToCompany(Hairdresser hairdresser){
        this.hairdressers.add(hairdresser);
    }

    private Company(Long id, String name, String documentNumber, List<Hairdresser> hairdressers) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.hairdressers = hairdressers;
    }

}
