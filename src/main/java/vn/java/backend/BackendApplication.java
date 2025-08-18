package vn.java.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BackendApplication {

	@Value("${jwt.secretKey}")
	private String jwtKey ;
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
	}

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));


		SpringApplication.run(BackendApplication.class, args);
	}


}
