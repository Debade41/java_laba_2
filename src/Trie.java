public class Trie {

    private static class Node {
        Node[] children = new Node[26];
        boolean isWord;
    }

    private final Node root = new Node();

    private int index(char c) {
        return c - 'a';
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = index(c);
            if (idx < 0 || idx >= 26) {
                throw new IllegalArgumentException("Недопустимый символ: " + c);
            }
            if (node.children[idx] == null) {
                node.children[idx] = new Node();
            }
            node = node.children[idx];
        }
        node.isWord = true;
    }

    public boolean contains(String word) {
        Node node = findNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    public MyList<String> getByPrefix(String prefix) {
        MyArrayList<String> result = new MyArrayList<>();

        Node node = findNode(prefix);
        if (node == null) {
            return result;
        }

        StringBuilder sb = new StringBuilder(prefix);
        dfs(node, sb, result);
        return result;
    }

    // Визуализатор дерева
    public void printTree() {
        System.out.println("(root)");
        StringBuilder prefix = new StringBuilder();
        printNode(root, prefix, 1);
    }

    /**
     * Рекурсивный вывод узла.
     * depth — текущая глубина (для отступов).
     */
    private void printNode(Node node, StringBuilder prefix, int depth) {
        for (int i = 0; i < 26; i++) {
            Node child = node.children[i];
            if (child != null) {
                char c = (char) ('a' + i);
                prefix.append(c);

                for (int d = 0; d < depth - 1; d++) {
                    System.out.print("  ");
                }

                System.out.print(" - " + c);

                if (child.isWord) {
                    System.out.print(" [word]");
                }
                System.out.println();

                printNode(child, prefix, depth + 1);

                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    // Вспомогательные методы

    private Node findNode(String s) {
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = index(c);
            if (idx < 0 || idx >= 26) {
                return null;
            }
            node = node.children[idx];
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    private void dfs(Node node, StringBuilder sb, MyArrayList<String> result) {
        if (node.isWord) {
            result.add(sb.toString());
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                sb.append(c);
                dfs(node.children[i], sb, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}