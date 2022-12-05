package Work27;


public class MyHashMap<k,v> implements MyMapInterface {
    // Начальный размер емкости --- исходный код 1 << 4
    private final int DEFAULT_INITIAL_CAPACITY = 16;
    // Коэффициент загрузки
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    // В соответствии с определенным статическим внутренним классом, инициализируем связанный список, длина является длиной по умолчанию
    Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];
    // длина
    private  int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object put(Object key, Object value) {
        // Рассчитать хеш-значение ключа
        int hashValue = hash(key);
        // Рассчитать место, где он должен храниться
        int i = indexFor(hashValue,table.length);
        // Если в i есть данные и ключ тот же, перезаписать
        for(Node node = table[i];node != null; node = node.next){
            Object k;
            if(node.hash == hashValue && ((k = node.key)==key||key.equals(k))){
                Object oldValue = node.value;
                node.value = value;
                return  oldValue;
            }
        }
        // Если в позиции i нет данных или есть данные в позиции i, но ключ - это новый ключ, добавьте узел
        addEntry(key,value,hashValue,i);
        return null;
    }


    @Override
    public Object get(Object key) {
        // Вычисляем значение хеша на основе хеш-кода объекта
        int hashValue = hash(key);
        // По значению хеша и длине связанного списка получаем индекс позиции вставки
        int i = indexFor(hashValue,table.length);
        for(Node node = table[i];node != null;node = node.next){
            if(node.key.equals(key) && hashValue == node.hash){
                return node.value;
            }
        }
        return null;
    }

    // Добавить элементы в Entry
    // hashvalue --- значение хеша
    // я --- индексная позиция
    public void addEntry(Object key,Object value,int hashValue,int i){
        // Если согласованная длина массива превышена, расширяем емкость
        if(++size >= table.length * DEFAULT_LOAD_FACTOR){
            Node[] newTable = new Node[table.length << 1];
            // копировать массив
            //System.arraycopy(table,0,newTable,0,table.length);
            Transfer(table,newTable);
            table = newTable;
        }
        // получить данные в i
        Node eNode = table[i];
        // Добавить узел, указать узел рядом с предыдущим узлом
        table[i] = new Node(hashValue,key,value,eNode);
    }

    // Цитировать скопированный код JDK1.7
    public void Transfer (Node [] src, Node [] newTable) {// src ссылается на старый массив Entry
        int newCapacity = newTable.length;
        for (int j = 0; j <src.length; j ++) {// пройти старый массив Entry
            Node e = src [j]; // Получить каждый элемент старого массива Entry
            if (e != null) {
                src [j] = null; // Освободить ссылку на объект старого массива Entry (после цикла for старый массив Entry больше не ссылается ни на какие объекты)
                do {
                    Node next = e.next;
                    int i = indexFor (e.hash, newCapacity); //! ! Пересчитать положение каждого элемента в массиве
                    e.next = newTable [i]; // Mark [1]
                    newTable [i] = e; // Поместить элемент в массив
                    e = next; // Доступ к элементам в следующей цепочке ввода
                } while (e != null);
            }
        }
    }


    // Получить позицию вставки (операция с модулем некорректна)
    public int indexFor(int hashValue,int length){
        return hashValue % length;
    }

    // Получить позицию вставки, получить значение хеш-кода в соответствии с хеш-кодом объекта Obeject
    public int hash(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static class Node implements MyMapInterface.Entry{
        // хэш-значение
        int hash;
        Object key;
        Object value;
        // Указывать на следующий узел (односвязный список)
        Node next;
        Node(int hash,Object key,Object value,Node next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }
    }
}