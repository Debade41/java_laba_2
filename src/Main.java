import java.util.Random;

public class Main {

    private static final Random rnd = new Random();

    // Генерация случайного слова длиной 3...7 символов
    private static String randomWord() {
        int len = 3 + rnd.nextInt(5); 
        StringBuilder s = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = (char) ('a' + rnd.nextInt(26));
            s.append(c);
        }
        return s.toString();
    }

    // Генерация случайного префикса длиной 1...2 из уже существующего слова
    private static String randomPrefix(String word) {
        int len = 1 + rnd.nextInt(Math.min(2, word.length()));
        return word.substring(0, len);
    }

    public static void main(String[] args) {

        System.out.println("=== Тестирование Trie ===");
        System.out.println();

        Trie trie = new Trie();

        int count = 10;
        String[] generated = new String[count];

        System.out.println("Сгенерированные слова:");
        for (int i = 0; i < count; i++) {
            generated[i] = randomWord();
            trie.insert(generated[i]);
            System.out.println(" - " + generated[i]);
        }

        System.out.println();

        // Проверка contains для всех слов
        System.out.println("Проверка contains():");
        for (String w : generated) {
            System.out.println("contains(\"" + w + "\") = " + trie.contains(w));
        }

        System.out.println();

        // Проверка startsWith() и getByPrefix() на случайных префиксах
        System.out.println("Проверка startsWith() и getByPrefix():");
        for (int i = 0; i < 5; i++) {
            String pref = randomPrefix(generated[rnd.nextInt(count)]);
            System.out.println();
            System.out.println("Префикс: \"" + pref + "\"");
            System.out.println("startsWith = " + trie.startsWith(pref));

            MyList<String> list = trie.getByPrefix(pref);
            System.out.println("getByPrefix:");
            for (int j = 0; j < list.size(); j++) {
                System.out.println("   * " + list.get(j));
            }
        }

        System.out.println();
        System.out.println("=== Тест завершён ===");
        
        System.out.println();
        System.out.println();
        System.out.println("=== Визуализатор дерева ===");
        trie.printTree();
    }
}