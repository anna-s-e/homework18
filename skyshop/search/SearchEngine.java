package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private List<Searchable> searchableItems;

    public SearchEngine(int capacity) {
        this.searchableItems = new ArrayList<>(capacity);
    }

    private int countOccurrences(String source, String substring) {
        if (source == null || substring == null || substring.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        String sourceLower = source.toLowerCase();
        String substringLower = substring.toLowerCase();

        while (true) {
            index = sourceLower.indexOf(substringLower, index);
            if (index == -1) {
                break;
            }
            count++;
            index += substringLower.length();
        }

        return count;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (searchableItems.isEmpty()) {
            throw new BestResultNotFound(search);
        }

        Searchable bestMatch = null;
        int maxOccurrences = 0;

        for (Searchable item : searchableItems) {
            String searchTerm = item.getSearchTerm();
            int occurrences = countOccurrences(searchTerm, search);

            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    public List<Searchable> search(String searchString) {
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase().contains(searchString.toLowerCase())){
                results.add(item);
            }
        }
        return results;
    }

    public int getCount() {
        return searchableItems.size();
    }

    public int getCapacity() {
        return Integer.MAX_VALUE;
    }
}