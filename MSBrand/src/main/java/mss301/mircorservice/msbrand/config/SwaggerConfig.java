package mss301.mircorservice.msbrand.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MSBrand Service API")
                        .version("1.0")
                        .description("Brand Management Service for BlindBox System")
                        .contact(new Contact()
                                .name("MSS301 Team")
                                .email("support@blindboxes.vn")));
    }
}
