package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String productName, int price) {
        super(productName);
        if (price<=0) {
            throw new IllegalArgumentException(
                    "Цена должна быть больше 0!"
            );
        }
        this.price = price;
    }

    @Override
    public int getPriceOfProduct() {
        return this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getPriceOfProduct();
    }
}
