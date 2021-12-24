package com.friday.fridayback.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User one = new User(
                    "last Name",
                    "Fist Name",
                    "Emailone",
                    "defeat"
            );
            User two = new User(
                    "last Name two",
                    "Fist Name two",
                    "Emailtwo",
                    "Password"
            );
            User three = new User(
                    "last Name three",
                    "Fist Name three",
                    "Emailthree",
                    "Password"
            );

            userRepository.saveAll(
                    List.of(one, two, three)
            );
        };
    }
}
