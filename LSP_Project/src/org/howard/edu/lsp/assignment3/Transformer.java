package org.howard.edu.lsp.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles applying transformation rules to products.
 */
public class Transformer {

    /**
     * Transforms a list of products into a list of transformed products.
     *
     * @param products list of raw products
     * @return list of transformed products
     */
    public List<ProductTransformed> transform(List<Product> products) {
        List<ProductTransformed> transformed = new ArrayList<>();

        for (Product p : products) {
            String name = p.getName().toUpperCase();

            double price = p.getPrice();
            if (p.getCategory().equalsIgnoreCase("Electronics")) {
                price *= 0.9; // apply discount
            }

            String category = p.getCategory();
            if (price > 1000) {
                category = "Premium " + category;
            }

            String priceRange = getPriceRange(price);

            transformed.add(new ProductTransformed(
                    p.getId(), name, price, category, priceRange));
        }

        return transformed;
    }

    /**
     * Determines the price range for a given price.
     *
     * @param price the price
     * @return price range as string
     */
    private String getPriceRange(double price) {
        if (price < 100) return "Budget";
        else if (price < 500) return "Mid-Range";
        else return "High-End";
    }
}
