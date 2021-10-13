package com.ims.image.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ImsImageAppApplication extends GlobalMethodSecurityConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(ImsImageAppApplication.class, args);
	}

}
