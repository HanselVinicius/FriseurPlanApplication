package com.hansel.FriseurPlan;

import org.springframework.boot.SpringApplication;

public class TestFriseurPlanApplication {

	public static void main(String[] args) {
		SpringApplication.from(FriseurPlanApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
