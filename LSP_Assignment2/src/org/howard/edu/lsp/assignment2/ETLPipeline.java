package org.howard.edu.lsp.assignment2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.*;
import java.util.*;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        List<Product> products = extract(inputPath);
        List<ProductTransformed> transformedProducts = transform(products);
        load(transformedProducts, outputPath);

        System.out.println("\nRun Summary: ");
        System.out.println("Rows read: " + products.size());
        System.out.println("Rows transformed: " + transformedProducts.size());
        System.out.println("Rows skipped: " + skippedRows);
        System.out.println("Output written to: " + outputPath);
        //System.out.println("Absolute path for output: " + new File(inputPath).getAbsolutePath()); 
        //System.out.println("Absolute path for output: " + new File(outputPath).getAbsolutePath()); //Added because i couldn't find the transformed_products.csv on my laptop. So this line of code showed me exactly where it was.

    }
    
    private static int skippedRows = 0; //keeps track of skipped rows

    // Extract
    public static List<Product> extract(String path) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skips header
            if (line == null) {
                System.out.println("Input file is empty. Creating output with header only.");
                return products;
            }

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                	skippedRows++;
                	continue; // skips malformed lines
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

    // Transform
    public static List<ProductTransformed> transform(List<Product> products) {
        List<ProductTransformed> transformed = new ArrayList<>();

        for (Product p : products) {
            String name = p.name.toUpperCase();

            double price = p.price;
            if (p.category.equals("Electronics")) {
                price = round(price * 0.9, 2); // 10% discount
            }

            String category = p.category;
            if (p.category.equals("Electronics") && price > 500.0) {
                category = "Premium Electronics";
            }

            String priceRange;
            if (price <= 10.00) priceRange = "Low";
            else if (price <= 100.00) priceRange = "Medium";
            else if (price <= 500.00) priceRange = "High";
            else priceRange = "Premium";

            transformed.add(new ProductTransformed(p.id, name, price, category, priceRange));
        }

        return transformed;
    }
    

    // Load
    public static void load(List<ProductTransformed> products, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();
            for (ProductTransformed p : products) {
                bw.write(p.id + "," + p.name + "," + p.price + "," + p.category + "," + p.priceRange);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper Method for Rounding
    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

// Product Transform
class ProductTransformed {
    int id;
    String name;
    double price;
    String category;
    String priceRange;

    ProductTransformed(int id, String name, double price, String category, String priceRange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }
}

class Product {
    int id;
    String name;
    double price;
    String category;

    Product(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
