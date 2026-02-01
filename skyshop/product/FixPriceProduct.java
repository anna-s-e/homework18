package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 50000;

    public FixPriceProduct(String productName) {
        super(productName);
    }

    @Override
    public int getPriceOfProduct() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getProductName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
