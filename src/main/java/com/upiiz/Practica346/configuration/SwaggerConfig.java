package com.upiiz.Practica346.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API de Cine - Gestión de Películas, Copias, Clientes y Préstamos",
                version = "1.0.0",
                description = "Documentación completa de la API REST del sistema de Cine",
                termsOfService = "https://www.upiiz.ipn.mx/",
                contact = @Contact(
                        name = "Luis Eduardo Espino Gtz",
                        email = "espinogutierrezluiseduardo@gmail.com",
                        url = "https://github.com/LuisEduardoEspinoGutierrez"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
        /*
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local")
        }
        */
)
public class SwaggerConfig { }
