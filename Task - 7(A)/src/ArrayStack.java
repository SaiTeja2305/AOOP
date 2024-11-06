import java.util.Arrays;

public class ArrayStack<T> implements Stack<T> {
    private T[] arr;
    private int top;
    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
        top = -1;
    }

    @Override
    public void push(T element) {
        if (top == capacity - 1) {
            // Resize the array when full
            resize();
        }
        arr[++top] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top--];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private void resize() {
        capacity = capacity * 2;
        arr = Arrays.copyOf(arr, capacity);
    }
}
