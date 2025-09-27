package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles reading raw product data from a CSV file.
 */
public class Extractor {
    private int skippedRows = 0;

    /**
     * Extracts product data from a CSV file.
     *
     * @param path path to input CSV file
     * @return list of Product objects (empty if none found)
     */
    public List<Product> extract(String path) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // read header
            if (line == null) {
                System.out.println("Input file is empty. Creating output with header only.");
                return products; // empty list
            }

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    skippedRows++;
                    continue; // skip malformed lines
                }

                try {
                    int id = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    double price = Double.parseDouble(fields[2].trim());
                    String category = fields[3].trim();

                    products.add(new Product(id, name, price, category));
                } catch (NumberFormatException e) {
                    skippedRows++;
                    System.out.println("Skipping line due to parse error: " + Arrays.toString(fields));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file 'data/products.csv' not found.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    /**
     * @return the number of skipped rows during extraction
     */
    public int getSkippedRows() {
        return skippedRows;
    }
}

