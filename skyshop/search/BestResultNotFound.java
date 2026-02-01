package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchQuery) {
        super ("Более подходящий объект для поиксового запроса не найден:" + searchQuery);
    }
}
