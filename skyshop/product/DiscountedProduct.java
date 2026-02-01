package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

    public DiscountedProduct(String productName, int basePrice, int discount) {
        super(productName);
        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Базовая цена продукта должна быть больше 0!"
            );
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException(
                    "Скидка должна быть в диапазоне от 0 до 100!"
            );
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPriceOfProduct() {
        int discountAmount = basePrice * discount / 100;
        return basePrice - discountAmount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getPriceOfProduct() + " (" + discount + "%)";
    }
}

