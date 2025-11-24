@SuppressWarnings("unchecked")
public class MyArrayList<T> implements MyList<T> {

    private T[] data;
    private int size;

    public MyArrayList() {
        data = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size++] = value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Увеличение внутреннего массива в 2 раза.
     */
    private void grow() {
        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}