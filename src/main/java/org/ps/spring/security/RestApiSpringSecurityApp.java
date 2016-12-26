package org.ps.spring.security;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = RestApiSpringSecurityApp.class)
public class RestApiSpringSecurityApp {

	public static void main(String[] args) {
		healthy(new SpringApplicationBuilder(RestApiSpringSecurityApp.class).banner(new PSBanner()).run(args));
	}

	private static void healthy(ConfigurableApplicationContext context) {
		log.info("Running {} !", context.getId());
	}

}

