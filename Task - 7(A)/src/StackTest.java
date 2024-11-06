public class StackTest {
    public static void main(String[] args) {
        // LinkedListStack Test
        Stack<Integer> linkedListStack = new LinkedListStack<>();
        Stack<String> linkedListStack1 = new LinkedListStack<>();

        linkedListStack.push(10);
        linkedListStack1.push("HELLO");
        System.out.println("LinkedListStack Peek: " + linkedListStack.peek());
        System.out.println("LinkedListStack Pop: " + linkedListStack.pop());
        System.out.println("LinkedListStack isEmpty: " + linkedListStack.isEmpty());

        // ArrayStack Test
        Stack<String> arrayStack = new ArrayStack<>(5);
        arrayStack.push("Hello");
        arrayStack.push("World");
        System.out.println("ArrayStack Peek: " + arrayStack.peek());
        System.out.println("ArrayStack Pop: " + arrayStack.pop());
        System.out.println("ArrayStack isEmpty: " + arrayStack.isEmpty());
    }
}
