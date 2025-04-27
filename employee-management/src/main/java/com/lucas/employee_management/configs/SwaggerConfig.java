package com.lucas.employee_management.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    Server server = new Server();
    server.setUrl("http://localhost:8080");
    server.setDescription("Development Server");

    Info info = new Info()
            .title("Employee Management System API")
            .version("1.0")
            .description("This API exposes endpoints to manage employees.");
    return new OpenAPI().info(info).servers(List.of(server));
  }
}
