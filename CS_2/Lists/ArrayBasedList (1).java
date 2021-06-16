import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayBasedList<T> implements List<T>, Iterable<T> {

    //initializing instance variables
    private T[] data;
    private int size;

    public ArrayBasedList(){
        data = (T[]) new Object[0];
    }

    //overriding our object to a String
    @Override
    public String toString(){
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (data[i] == null){
                continue;
            }
            result += data[i];
            if (i<size-1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayBasedList<?> that = (ArrayBasedList<?>) o;
        if (size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    //adding items to our array
    @Override
    public void add(T item) {
        if (size == data.length) {
            T[] temp = (T[]) new Object[data.length + 1];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        data[size] = item;
        size++;
    }

    //clearing out our array
    @Override
    public void clear() {
        this.size = 0;
    }

    //finds the needed item in the array, no matter what its position is
    @Override
    public boolean contains(T item) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    //returns element at position i
    @Override
    public T get(int i) {
        return data[i];
    }

    //returns index of element equaling the item
    @Override
    public int indexOf(T item) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    //removes element at given index
    @Override
    public void removeAt(int i) {
        int k = 0;
        T[] temp = (T[]) new Object[data.length];
        for (int j = 0; j < data.length; j++) {
            if (j == i) {
                continue;
            }
            temp[k] = data[j];
            k++;
        }
        data = temp;
        size--;
    }

    //replaces an item in the list with item at given index
    @Override
    public void set(int i, T item) {
        data[i] = item;
    }

    //returns size of new array
    @Override
    public int size() {
        return size;
    }

    private class ArrayBasedListIterator implements Iterator<T> {

        private int index;

        public ArrayBasedListIterator() {
            index = size - 1;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            T result = data[index];
            index--;
            return result;
        }
    }

    //make it generic using an iterator
    @Override
    public Iterator<T> iterator() {

        return new ArrayBasedListIterator();
    }
}
