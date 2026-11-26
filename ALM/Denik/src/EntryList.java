/** Custom list backed by a fixed-size array; when full, the array is replaced by one twice as large. */
public class EntryList {
    private String[] items = new String[4];
    private int size = 0;

    public int size() {
        return size;
    }

    public void add(String text) {
        if (size == items.length) {
            String[] bigger = new String[items.length * 2];
            for (int i = 0; i < size; i++) {
                bigger[i] = items[i];
            }
            items = bigger;
        }
        items[size++] = text;
    }

    public String get(int index) {
        checkIndex(index);
        return items[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--size] = null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index);
        }
    }
}