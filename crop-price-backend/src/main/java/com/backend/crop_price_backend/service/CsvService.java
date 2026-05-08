package com.backend.crop_price_backend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crop_price_backend.model.Crop;
import com.backend.crop_price_backend.model.Price;
import com.backend.crop_price_backend.repo.CropRepository;
import com.backend.crop_price_backend.repo.PriceRepository;

@Service
public class CsvService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private PriceRepository priceRepository;

    public void processFile(File file) {

        System.out.println("Processing file: " + file.getName());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            // Skip header
            br.readLine();

            int lineNumber = 1;

            while ((line = br.readLine()) != null) {

                lineNumber++;

                try {

                    // Skip empty lines
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] data = line.split(",");

                    // Validate column count
                    if (data.length < 7) {

                        System.out.println(
                                "Skipping invalid row at line "
                                        + lineNumber
                                        + " -> insufficient columns");

                        continue;
                    }

                    // Safe trimming
                    String dateStr = data[0].trim();
                    String commodity = data[1].trim();
                    String state = data[2].trim();
                    String market = data[3].trim();

                    // Skip invalid mandatory fields
                    if (commodity.isEmpty()
                            || market.isEmpty()
                            || dateStr.isEmpty()) {

                        System.out.println(
                                "Skipping invalid row at line "
                                        + lineNumber
                                        + " -> mandatory field missing");

                        continue;
                    }

                    // Safe number parsing
                    Double avgPrice = parseDoubleSafe(data[4]);
                    Double minPrice = parseDoubleSafe(data[5]);
                    Double maxPrice = parseDoubleSafe(data[6]);

                    // Safe date parsing
                    LocalDate date;

                    try {
                        date = LocalDate.parse(dateStr);

                    } catch (Exception e) {

                        System.out.println(
                                "Invalid date at line "
                                        + lineNumber
                                        + " -> "
                                        + dateStr);

                        continue;
                    }

                    // Find existing crop
                    Optional<Crop> existingCrop =
                            cropRepository.findByName(commodity);

                    Crop crop;

                    if (existingCrop.isPresent()) {

                        crop = existingCrop.get();

                    } else {

                        crop = new Crop();
                        crop.setName(commodity);

                        crop = cropRepository.save(crop);
                    }

                    // Create price entity
                    Price price = new Price();

                    price.setCrop(crop);
                    price.setState(state);
                    price.setMarket(market);
                    price.setAvgPrice(avgPrice);
                    price.setMinPrice(minPrice);
                    price.setMaxPrice(maxPrice);
                    price.setDate(date);
                    price.setSourceFile(file.getName());

                    try {

                        priceRepository.save(price);

                    } catch (Exception e) {

                        System.out.println(
                                "Duplicate skipped at line "
                                        + lineNumber);
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Error processing line "
                                    + lineNumber);

                    e.printStackTrace();
                }
            }

            System.out.println(
                    "Completed file: "
                            + file.getName());

        } catch (Exception e) {

            System.out.println(
                    "Failed to process file: "
                            + file.getName());

            e.printStackTrace();
        }
    }

    // Safe double parser
    private Double parseDoubleSafe(String value) {

        try {

            if (value == null) {
                return 0.0;
            }

            value = value.trim();

            if (value.isEmpty()
                    || value.equalsIgnoreCase("NA")
                    || value.equalsIgnoreCase("null")
                    || value.equals("-")) {

                return 0.0;
            }

            return Double.parseDouble(value);

        } catch (Exception e) {

            return 0.0;
        }
    }
}