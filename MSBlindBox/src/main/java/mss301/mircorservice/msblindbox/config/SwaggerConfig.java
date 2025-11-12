package mss301.mircorservice.msblindbox.config;

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
                        .title("Service API")
                        .version("1.0")
                        .description("BlindBox Management Service for BlindBox System")
                        .contact(new Contact()
                                .name("ThangNTSE172839")
                                .email("thangntse172839@fpt.edu.vn")));
    }
}
