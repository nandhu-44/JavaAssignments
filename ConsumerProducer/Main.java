public class Main {
    public static void main(String[] args) {
        Value value = new Value();
        new Thread(new Producer(value), "Producer").start();
        new Thread(new Consumer(value), "Consumer").start();
    }
}
