package com.backend.crop_price_backend.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.backend.crop_price_backend.service.CsvService;

@Component
public class CsvRunner implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=== CSV RUNNER STARTED ===");

        // Main folders
        File incomingFolder = new File("../data/incoming");
        File processedFolder = new File("../data/processed");
        File failedFolder = new File("../data/failed");

        // Create folders if not exist
        processedFolder.mkdirs();
        failedFolder.mkdirs();

        System.out.println(
                "Incoming Path: "
                        + incomingFolder.getAbsolutePath());

        File[] files = incomingFolder.listFiles();

        if (files == null || files.length == 0) {

            System.out.println("No files found!");

            return;
        }

        for (File file : files) {

            // Skip directories
            if (file.isDirectory()) {
                continue;
            }

            try {

                System.out.println(
                        "Processing: "
                                + file.getName());

                // Process CSV
                csvService.processFile(file);

                Thread.sleep(500);
                
                // Move to processed
                File destination =
                        new File(
                                processedFolder,
                                file.getName());

                Files.move(
                        file.toPath(),
                        destination.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println(
                        "DONE: "
                                + file.getName());

            } catch (Exception e) {

                System.out.println(
                        "FAILED: "
                                + file.getName());

                e.printStackTrace();

                try {

                    // Move failed file
                    File destination =
                            new File(
                                    failedFolder,
                                    file.getName());
                    Thread.sleep(500);
                    
                    Files.move(
                            file.toPath(),
                            destination.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception moveError) {

                    System.out.println(
                            "Could not move failed file: "
                                    + file.getName());

                    moveError.printStackTrace();
                }
            }
        }

        System.out.println("=== CSV RUNNER FINISHED ===");
    }
}