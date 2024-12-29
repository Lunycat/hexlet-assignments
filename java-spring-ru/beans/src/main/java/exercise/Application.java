package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Daytime getLocalDate() {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (localDateTime.getHour() > 5 && localDateTime.getHour() < 23) {
            return new Day();
        } else {
            return new Night();
        }
    }
}
