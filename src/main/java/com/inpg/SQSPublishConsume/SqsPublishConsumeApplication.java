package com.inpg.SQSPublishConsume;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsPublishConsumeApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SqsPublishConsumeApplication.class, args);
		} catch (Throwable e) {
			e.printStackTrace();
			// connection timed out...let's try again
		}

	}
}
