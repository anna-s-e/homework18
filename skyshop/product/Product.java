package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private String productName;

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException(
                    "Название продукта не может быть null, пустой строкой или только пробелы!"
            );
        }
        this.productName = productName;
    }

    public String getProductName() {
        return this.productName;
    }

    public abstract int getPriceOfProduct();

    public abstract boolean isSpecial();

    @Override
    public abstract String toString();

    @Override
    public String getSearchTerm() {
        return getProductName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return getProductName();
    }
}
