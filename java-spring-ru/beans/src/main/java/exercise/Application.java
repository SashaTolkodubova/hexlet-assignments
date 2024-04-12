package exercise;

import org.hibernate.grammars.hql.HqlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    public Daytime getDaytime() {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        int currentHour = localDateTimeNow.getHour();
        if ((6 < currentHour) &&  (currentHour<= 22)) {
            return new Day();
        }
        return new Night();
    }
    // END
}
