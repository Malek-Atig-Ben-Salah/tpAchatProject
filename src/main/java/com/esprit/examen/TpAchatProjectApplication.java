package com.esprit.examen;


import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;


@EnableScheduling
@SpringBootApplication
public class TpAchatProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(TpAchatProjectApplication.class, args);
}

}