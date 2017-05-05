package com.jpmorgan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application to manage sales sent by external companies
 * 
 * @author Carlos Alberto
 *
 */
@SpringBootApplication
public class MessageProcessingApplication {

    /**
     * Starts application
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(final String[] args) throws InterruptedException {
        SpringApplication.run(MessageProcessingApplication.class, args);
    }
}