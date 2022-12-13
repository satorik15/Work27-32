package Work30_1.Task2;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Узел дерева
class Node
{
    Character ch;
    Integer freq;
    Node left = null, right = null;

    Node(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(Character ch, Integer freq, Node left, Node right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

class Main
{
    // Проходим по дереву Хаффмана и сохраняем коды Хаффмана на карте.
    public static void encode(Node root, String str,
                              Map<Character, String> huffmanCode)
    {
        if (root == null) {
            return;
        }

        // Найден листовой узел
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }

    // Проходим по дереву Хаффмана и декодируем закодированную строку
    public static int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null) {
            return index;
        }

        // Найден листовой узел
        if (isLeaf(root))
        {
            System.out.print(root.ch);
            return index;
        }

        index++;

        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }

    // Вспомогательная функция для проверки, содержит ли дерево Хаффмана только один узел
    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    // Строит дерево Хаффмана и декодирует заданный входной текст
    public static void buildHuffmanTree(String text)
    {
        // Базовый случай: пустая строка
        if (text == null || text.length() == 0) {
            return;
        }

        // Подсчитаем частоту появления каждого символа
        // и сохранить его на карте

        Map<Character, Integer> freq = new HashMap<>();
        for (char c: text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // создаем приоритетную очередь для хранения живых узлов дерева Хаффмана.
        // Обратите внимание, что элемент с наивысшим приоритетом имеет наименьшую частоту

        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

        // создаем конечный узел для каждого символа и добавляем его
        // в приоритетную очередь.

        for (var entry: freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // делаем до тех пор, пока в queue не окажется более одного узла
        while (pq.size() != 1)
        {
            // Удаляем два узла с наивысшим приоритетом
            // (самая низкая частота) из queue

            Node left = pq.poll();
            Node right = pq.poll();

            // создаем новый внутренний узел с этими двумя узлами в качестве дочерних
            // и с частотой равной сумме обоих узлов
            // частоты. Добавьте новый узел в очередь приоритетов.

            int sum = left.freq + right.freq;
            pq.add(new Node(null, sum, left, right));
        }

        // `root` хранит указатель на корень дерева Хаффмана
        Node root = pq.peek();

        // Проходим по дереву Хаффмана и сохраняем коды Хаффмана на карте
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        // Выводим коды Хаффмана
        System.out.println("Huffman Codes are: " + huffmanCode);
        System.out.println("The original string is: " + text);

        // Печатаем закодированную строку
        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        System.out.println("The encoded string is: " + sb);
        System.out.print("The decoded string is: ");

        if (isLeaf(root))
        {
            // Особый случай: для ввода типа a, aa, aaa и т. д.
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        }
        else {
            // Снова проходим по дереву Хаффмана и на этот раз
            // декодируем закодированную строку
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
    }

    // Реализация алгоритма кодирования Хаффмана на Java
    public static void main(String[] args)
    {
        String text="2,3,5,7,11,13,17,19,23,29,31,37,41";
        buildHuffmanTree(text);
    }
}