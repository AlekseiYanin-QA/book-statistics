package com.edu.bookstatistics;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStatisticsApplication {
public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
    System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));

    SpringApplication.run(BookStatisticsApplication.class, args);
}
}