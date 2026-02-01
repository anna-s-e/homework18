package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.Arrays;

import org.skypro.skyshop.article.Article;

public class App {
    public static void main(String[] args) {
        System.out.println("Проверка правильности заведения продукта");
        try {
            Product phone = new SimpleProduct("Телефон", 75000);
            System.out.println("Создан продукт: " + phone.getProductName());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания продукта: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на 'пустое название'");
        try {
            Product invalid1 = new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (пустое название): " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на 'название из пробелов'");
        try {
            Product invalid2 = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (название из пробелов): " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на цену, не входящую в заданный диапазон");
        try {
            Product invalid3 = new SimpleProduct("Телефон", -100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (цена <= 0)): " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на скидку, не входящую в заданный диапазон");
        try {
            Product invalid4 = new DiscountedProduct("Ноутбук", 80000, -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (скидка < 0): " + e.getMessage());
        }

        try {
            Product invalid5 = new DiscountedProduct("Ноутбук", 80000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (скидка > 100): " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на скидку, находящуюся на границе заданного диапазона");
        try {
            Product validDiscount1 = new DiscountedProduct("Мышь компьютерная", 10000, 0);
            System.out.println("Успешно создан продукт со скидкой 0%: " + validDiscount1.getProductName());

            Product validDiscount2 = new DiscountedProduct("Блок питания", 50000, 100);
            System.out.println("Успешно создан продукт со скидкой 100%: " + validDiscount2.getProductName());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания продукта со скидкой: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Проверка на поиск наиболее подходящего объекта");
        SearchEngine searchEngine = new SearchEngine(15);
        Product phone = new SimpleProduct("Телефон iPhone", 75000);
        Product laptop = new DiscountedProduct("Ноутбук игровой", 80000, 10);
        Product phone2 = new FixPriceProduct("Телефон Samsung ");
        Product tablet = new SimpleProduct("Планшет Apple iPad", 70000);

        Article article1 = new Article(
                "Как выбрать смартфон",
                "Современные смартфоны отличаются процессорами, камерами и объемом памяти..... "
        );

        Article article2 = new Article(
                "Игровые ноутбуки 2024",
                "Обзор лучших игровых ноутбуков этого года..... "
        );

        Article article3 = new Article(
                "Смартфон или планшет? Что лучше?",
                "Сравнение смартфонов и планшетов для работы и развлечений... "
        );

        Article article4 = new Article(
                "Apple против Samsung",
                "Сравнение продуктов Apple и Samsung: iPhone vs Galaxy... "
        );
        searchEngine.add(phone);
        searchEngine.add(laptop);
        searchEngine.add(phone2);
        searchEngine.add(tablet);
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);
        searchEngine.add(article4);
        System.out.println("Добавлено элементов: " + searchEngine.getCount());
        System.out.println();
        System.out.println("Поиск по слову 'смартфон'");
        try {
            Searchable bestMatch1 = searchEngine.findBestMatch("смартфон");
            System.out.println(bestMatch1.getStringRepresentation());
            System.out.println(bestMatch1.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Поиск по слову 'игровой'");
        try {
            Searchable bestMatch2 = searchEngine.findBestMatch("игровой");
            System.out.println(bestMatch2.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Поиск по слову 'Apple'");
        try {
            Searchable bestMatch3 = searchEngine.findBestMatch("Apple");
            System.out.println(bestMatch3.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Поиск несуществующего слова 'автомобиль'");
        try {
            Searchable bestMatch4 = searchEngine.findBestMatch("автомобиль");
            System.out.println(bestMatch4.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Поиск пустой строки");
        try {
            Searchable bestMatch6 = searchEngine.findBestMatch("");
            System.out.println("Найден лучший результат: " + bestMatch6.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Код предыдущей работы");
        System.out.println();
        System.out.println("Добавлено элементов: " + searchEngine.getCount());
        System.out.println("Проверка поиска по слову 'Телефон'");
        Searchable[] results1 = searchEngine.search("Телефон");
        System.out.println("Результат: " + Arrays.toString(results1));
        System.out.println("Детализация:");
        for (int i = 0; i < results1.length; i++) {
            if (results1[i] != null) {
                System.out.println((i + 1) + ". " + results1[i].getStringRepresentation());
            }
        }
        System.out.println();

        System.out.println("Поиск по слову 'выбрать' (встречается в статьях)");
        Searchable[] results2 = searchEngine.search("выбрать");
        System.out.println("Результат: " + Arrays.toString(results2));
        System.out.println("Детализация:");
        for (int i = 0; i < results2.length; i++) {
            if (results2[i] != null) {
                System.out.println((i + 1) + ". " + results2[i].getStringRepresentation());
            }
        }
        System.out.println();

        System.out.println("Поиск по части слова 'план'");
        Searchable[] results3 = searchEngine.search("план");
        System.out.println("Результат: " + Arrays.toString(results3));
        System.out.println("Детализация:");
        for (Searchable result : results3) {
            if (result != null) {
                System.out.println("• " + result.getStringRepresentation());
            }
        }
        System.out.println();

        System.out.println("Поиск по несуществующему слову 'автомобиль'");
        Searchable[] results6 = searchEngine.search("автомобиль");
        System.out.println("Результаты: " + Arrays.toString(results6));
        System.out.println("(все элементы null - ничего не найдено)");
        System.out.println();

        System.out.println("Проверка ограничения в 5 результатов");
        System.out.println("Поиск по букве 'и' :");
        Searchable[] results7 = searchEngine.search("и");
        System.out.println("Найдено результатов: " + countNonNull(results7) + " (максимум 5)");
        System.out.println("Результаты: " + Arrays.toString(results7));

        ProductBasket basket1 = new ProductBasket();
        System.out.println("1. Добавление продукта в корзину:");
        basket1.addProduct(phone);
        basket1.addProduct(laptop);
        basket1.printContents();
        System.out.println("2. Добавление продуктов в переполненную корзину:");
        ProductBasket basket2 = new ProductBasket();
        basket2.addProduct(phone2);
        basket2.addProduct(tablet);
        basket2.addProduct(phone);
        basket2.addProduct(phone2);
        basket2.printContents();
        System.out.println("3. Печать содержимого корзины с несколькими товарами:");
        basket1.printContents();
        System.out.println("4. Получение стоимости корзины с несколькими товарами:");
        int totalPrice = basket1.getTotalPrice();
        System.out.println("Общая стоимость корзины: " + totalPrice + " рублей");
        System.out.println("5/6. Поиск товаров, которые есть и нет в корзине:");
        boolean searchPhone = basket1.containsProduct("Телефон ");
        boolean searchMouse = basket1.containsProduct("Мышь ");
        System.out.println(searchPhone);
        System.out.println(searchMouse);
        System.out.println("7. Очистка корзины");
        basket1.clearBasket();
        basket1.printContents();
        System.out.println("9. Получение стоимости пустой корзины:");
        totalPrice = basket1.getTotalPrice();
        System.out.println("Общая стоимость корзины: " + totalPrice + " рублей");
        System.out.println("10. Поиск товара по имени в пустой корзине:");
        boolean searchPhone2 = basket1.containsProduct("Телефон");
        System.out.println(searchPhone2);
    }

    private static int countNonNull(Searchable[] array) {
        int count = 0;
        for (Searchable item : array) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }
}
