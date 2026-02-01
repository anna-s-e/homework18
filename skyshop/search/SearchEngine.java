package org.skypro.skyshop.search;

public class SearchEngine {
    private Searchable[] searchableItems;
    private int count;

    public SearchEngine(int capacity) {
        this.searchableItems = new Searchable[capacity];
        this.count = 0;
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
        if (count == 0) {
            throw new BestResultNotFound(search);
        }

        Searchable bestMatch = null;
        int maxOccurrences = 0;

        for (int i = 0; i < count; i++) {
            Searchable item = searchableItems[i];
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
        if (count < searchableItems.length) {
            searchableItems[count] = item;
            count++;
        } else {
            System.out.println("Поиск переполнен! Не удалось добавить: " + item.getName());
        }
    }

    public Searchable[] search(String searchString) {
        Searchable[] results = new Searchable[5];
        int foundCount = 0;
        for (int i = 0; i < count; i++) {
            Searchable item = searchableItems[i];
            if (item.getSearchTerm().toLowerCase().contains(searchString.toLowerCase())) {
                results[foundCount] = item;
                foundCount++;
                if (foundCount >= 5) {
                    break;
                }
            }
        }
        return results;
    }

    public int getCount() {
        return count;
    }

    public int getCapacity() {
        return searchableItems.length;
    }
}