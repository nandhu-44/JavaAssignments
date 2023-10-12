public class Buffer {

    private int limit;
    private int[] buffer;
    private int count;

    public Buffer(int limit) {
        this.limit = limit;
        this.buffer = new int[limit];
        this.count = 0;
    }

    public synchronized void add() throws InterruptedException {
        while (count == limit) {
            wait();
            System.out.println("Buffer is full, waiting for remover...");
            // Thread.sleep(1000);
        }
        int value = count + 1;
        buffer[count] = value;
        count++;
        System.out.println(Thread.currentThread().getName() + " added: " + value);
        notify();
    }

    public synchronized void remove() throws InterruptedException {
        while (count == 0) {
            wait();
            System.out.println("Buffer is empty, waiting for adder...");
            // Thread.sleep(1000);
        }
        int value = buffer[count - 1];
        count--;
        System.out.println(Thread.currentThread().getName() + " removed: " + value);
        notify();
    }
}
