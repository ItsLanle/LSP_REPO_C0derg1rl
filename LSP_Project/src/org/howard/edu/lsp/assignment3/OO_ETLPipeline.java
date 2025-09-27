// Author: Ayotunde Ogunruku

package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Orchestrates the ETL pipeline (Extract → Transform → Load) for Assignment 3.
 */
public class OO_ETLPipeline {

    public static void main(String[] args) {
        String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";

        // Extract
        Extractor extractor = new Extractor();
        List<Product> products = extractor.extract(inputFile);

        // Transform
        Transformer transformer = new Transformer();
        List<ProductTransformed> transformed = transformer.transform(products);

        // Load
        Loader loader = new Loader();
        loader.load(transformed, outputFile);

        // Run Summary
        System.out.println("\nRun Summary: ");
        System.out.println("Rows read: " + products.size());
        System.out.println("Rows transformed: " + transformed.size());
        System.out.println("Rows skipped: " + extractor.getSkippedRows());
        System.out.println("Output written to: " + outputFile);
    }
}











///**
// * Name: Ayotunde Ogunruku
// */
//package org.howard.edu.lsp.assignment3;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.*;
//
//// ETL Pipeline orchestrator
//public class OO_ETLPipeline {
//
//    public static void main(String[] args) {
//        String inputPath = "data/products.csv";
//        String outputPath = "data/transformed_products.csv";
//
//        Extractor extractor = new Extractor(inputPath);
//        List<Product> products = extractor.extract();
//
//        Transformer transformer = new Transformer();
//        List<ProductTransformed> transformedProducts = transformer.transform(products);
//
//        Loader loader = new Loader(outputPath);
//        loader.load(transformedProducts);
//
//        System.out.println("\nRun Summary:");
//        System.out.println("Rows read: " + products.size());
//        System.out.println("Rows transformed: " + transformedProducts.size());
//        System.out.println("Rows skipped: " + extractor.getSkippedRows());
//        System.out.println("Output written to: " + outputPath);
//    }
//}
//
//// Product represents a raw CSV row
//class Product {
//    private int id;
//    private String name;
//    private double price;
//    private String category;
//
//    public Product(int id, String name, double price, String category) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//    }
//
//    public int getId() { return id; }
//    public String getName() { return name; }
//    public double getPrice() { return price; }
//    public String getCategory() { return category; }
//
//    public void setName(String name) { this.name = name; }
//    public void setPrice(double price) { this.price = price; }
//    public void setCategory(String category) { this.category = category; }
//}
//
//// ProductTransformed represents a processed row
//class ProductTransformed {
//    private int id;
//    private String name;
//    private double price;
//    private String category;
//    private String priceRange;
//
//    public ProductTransformed(int id, String name, double price, String category, String priceRange) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.priceRange = priceRange;
//    }
//
//    public int getId() { return id; }
//    public String getName() { return name; }
//    public double getPrice() { return price; }
//    public String getCategory() { return category; }
//    public String getPriceRange() { return priceRange; }
//}
//
//// Extractor reads CSV and converts to Product objects
//class Extractor {
//    private String inputPath;
//    private int skippedRows = 0;
//
//    public Extractor(String inputPath) {
//        this.inputPath = inputPath;
//    }
//
//    public List<Product> extract() {
//        List<Product> products = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
//            String line = br.readLine(); // skip header
//            if (line == null) {
//                System.out.println("Input file is empty. Creating output with header only.");
//                return products;
//            }
//
//            while ((line = br.readLine()) != null) {
//                String[] fields = line.split(",");
//                if (fields.length != 4) {
//                    skippedRows++;
//                    continue; // skip malformed line
//                }
//
//                try {
//                    int id = Integer.parseInt(fields[0].trim());
//                    String name = fields[1].trim();
//                    double price = Double.parseDouble(fields[2].trim());
//                    String category = fields[3].trim();
//                    products.add(new Product(id, name, price, category));
//                } catch (NumberFormatException e) {
//                    skippedRows++;
//                    System.out.println("Skipping line due to parse error: " + Arrays.toString(fields));
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Error: Input file '" + inputPath + "' not found.");
//            System.exit(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return products;
//    }
//
//    public int getSkippedRows() { return skippedRows; }
//}
//
//// Transformer handles all product transformations
//class Transformer {
//
//    public List<ProductTransformed> transform(List<Product> products) {
//        List<ProductTransformed> transformed = new ArrayList<>();
//
//        for (Product p : products) {
//            String name = p.getName().toUpperCase();
//            double price = p.getPrice();
//            String category = p.getCategory();
//
//            if (category.equals("Electronics")) {
//                price = round(price * 0.9, 2); // 10% discount
//            }
//
//            if (category.equals("Electronics") && price > 500.0) {
//                category = "Premium Electronics";
//            }
//
//            String priceRange;
//            if (price <= 10.0) priceRange = "Low";
//            else if (price <= 100.0) priceRange = "Medium";
//            else if (price <= 500.0) priceRange = "High";
//            else priceRange = "Premium";
//
//            transformed.add(new ProductTransformed(p.getId(), name, price, category, priceRange));
//        }
//
//        return transformed;
//    }
//
//    // Helper method to round to 2 decimal places
//    private double round(double value, int places) {
//        BigDecimal bd = new BigDecimal(Double.toString(value));
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
//}
//
//// Loader writes transformed products to CSV
//class Loader {
//    private String outputPath;
//
//    public Loader(String outputPath) {
//        this.outputPath = outputPath;
//    }
//
//    public void load(List<ProductTransformed> products) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
//            bw.write("ProductID,Name,Price,Category,PriceRange");
//            bw.newLine();
//
//            for (ProductTransformed p : products) {
//                bw.write(p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getCategory() + "," + p.getPriceRange());
//                bw.newLine();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
