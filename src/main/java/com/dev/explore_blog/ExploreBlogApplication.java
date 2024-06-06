package com.dev.explore_blog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title=" Explore ",
				description = "Explore Application Spring Boot Rest APIs Documentation",
				version = "1.0"
		)
)

public class ExploreBlogApplication {

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ExploreBlogApplication.class, args);
	}

}
