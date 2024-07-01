package com.eltro.DAPI.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student Marta = new Student(
                "Marta",
                "marta@nz.co",
                LocalDate.of(2000, Month.FEBRUARY, 19)
            );

            Student Alex = new Student(
                "Alex",
                "alex@nz.co",
                LocalDate.of(2001, Month.FEBRUARY, 3)
            );

            // repository.saveAll(
            //     List.of(Marta, Alex)
            // );
        };
    }

}
