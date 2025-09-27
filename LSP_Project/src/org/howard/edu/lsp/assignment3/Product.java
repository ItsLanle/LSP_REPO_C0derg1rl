package org.howard.edu.lsp.assignment3;

/**
 * Represents a raw product with basic fields extracted from the CSV file.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String category;

    /**
     * Constructs a Product object.
     *
     * @param id       the product ID
     * @param name     the product name
     * @param price    the product price
     * @param category the product category
     */
    public Product(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
