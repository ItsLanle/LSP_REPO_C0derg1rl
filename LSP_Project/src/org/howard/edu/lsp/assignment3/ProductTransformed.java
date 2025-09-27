package org.howard.edu.lsp.assignment3;

/**
 * Represents a transformed product after applying transformation rules.
 */
public class ProductTransformed {
    private int id;
    private String name;
    private double price;
    private String category;
    private String priceRange;

    /**
     * Constructs a transformed product object.
     *
     * @param id         product ID
     * @param name       transformed product name
     * @param price      transformed product price
     * @param category   transformed product category
     * @param priceRange derived price range
     */
    public ProductTransformed(int id, String name, double price, String category, String priceRange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
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

    public String getPriceRange() {
        return priceRange;
    }
}
