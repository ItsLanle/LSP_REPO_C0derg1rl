package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles writing transformed products to a CSV file.
 */
public class Loader {

    /**
     * Writes transformed products to a CSV file.
     *
     * @param products list of transformed products
     * @param filePath output CSV file path
     */
    public void load(List<ProductTransformed> products, String filePath) {
        if (products == null || products.isEmpty()) {
            System.out.println("⚠️ No products to load.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id,name,price,category,priceRange\n");
            for (ProductTransformed p : products) {
                writer.write(p.getId() + "," + p.getName() + "," +
                             p.getPrice() + "," + p.getCategory() + "," +
                             p.getPriceRange() + "\n");
            }
            System.out.println("✅ Data successfully written to " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error writing file: " + e.getMessage());
        }
    }
}
