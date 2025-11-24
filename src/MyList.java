public interface MyList<T> {

    /**
     * Добавить элемент в конец списка.
     */
    void add(T value);

    /**
     * Получить элемент по индексу.
     * @throws IndexOutOfBoundsException если индекс некорректен.
     */
    T get(int index);

    /**
     * Текущее количество элементов в списке.
     */
    int size();
}