package com.purnima.campusconnect;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampusConnectApplication {
    public static void main(String[] args) {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        SpringApplication.run(CampusConnectApplication.class, args);
    }
}
