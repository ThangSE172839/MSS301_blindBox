package mss301.mircorservice.msblindbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsBlindBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBlindBoxApplication.class, args);
    }

}
