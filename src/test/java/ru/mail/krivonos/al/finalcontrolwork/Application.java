package ru.mail.krivonos.al.finalcontrolwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ru.mail.krivonos.al")
@EntityScan(basePackages = "ru.mail.krivonos.al.finalcontrolwork.repository.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
