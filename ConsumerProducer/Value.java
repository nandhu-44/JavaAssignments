import java.util.Random;

public class Value {

    private boolean hasValue = false;
    private int value;
    private Random random = new Random();
    public Value() {
        this.value = 0;
        this.hasValue = false;
    }

    public synchronized void produce() throws InterruptedException {
        while (hasValue) {
            wait();
            System.out.println("Value available, waiting for consumer...");
        }
        this.value = random.nextInt(100);
        this.hasValue = true;
        System.out.println(Thread.currentThread().getName() + " produced value :  " + this.value);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasValue) {
            wait();
            System.out.println("Value not available, waiting for producer...");
        }
        int consumedValue = this.value;
        this.hasValue = false;
        this.value = 0;
        System.out.println(Thread.currentThread().getName() + " consumed value : " + consumedValue);
        notify();
    }
}
