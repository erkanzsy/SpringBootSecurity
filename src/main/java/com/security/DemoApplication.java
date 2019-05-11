package com.security;

import com.security.db.UserRepository;
import com.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin", passwordEncoder.encode("admin"),"ADMIN","READ,WRITE");
        User user = new User("user", passwordEncoder.encode("user"),"USER","READ");
        User manager = new User("manager", passwordEncoder.encode("manager"),"MANAGEMENT","WRITE");

        List<User> users = Arrays.asList(admin,user,manager);

        userRepository.saveAll(users);
    }
}
