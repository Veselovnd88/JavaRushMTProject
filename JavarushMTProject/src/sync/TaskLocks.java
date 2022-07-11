package sync;

public class TaskLocks {
    private static final int NUMBER_LOCKS = 12;
    private final Node[] buckets;
    private final Object[] locks;

    static class Node {
        public Node next;
        public Object key;
        public Object value;
    }

    public TaskLocks(int numberBuckets) {//конструктор принимает количество корзин
        buckets = new Node[numberBuckets];// ициниализировали массив нодов
        locks = new Object[NUMBER_LOCKS];//создали массив с локами 12 по умолчанию
        for (int i = 0; i < NUMBER_LOCKS; i++) {
            locks[i] = new Object();//создали по одному объекту в массиве
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    } // хешкод

    public Object get(Object key) {// получить ключ
        int hash = hash(key);
        
        synchronized (locks[hash%NUMBER_LOCKS]) {// выбирает номер лока
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) return m.value;// возвращает объект. если нить зашла в этот объект то другие нити сюда не пустит
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i%NUMBER_LOCKS]) {
                buckets[i] = null;
            }//обнуление 
        }
    }

    public static void main(String[] args) {

    }
}
