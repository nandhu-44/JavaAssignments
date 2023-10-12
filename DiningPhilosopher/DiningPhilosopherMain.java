import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
    private ReentrantLock leftFork;
    private ReentrantLock rightFork;
    private int id;

    public Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork) {
        this.id = id + 1;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            think();
            leftFork.lock();
            System.out.println("Philosopher " + id + " picked up the left fork.");
            rightFork.lock();
            System.out.println("Philosopher " + id + " picked up the right fork and is now eating.");
            eat();
            leftFork.unlock();
            rightFork.unlock();
        }
    }
}

public class DiningPhilosopherMain {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        ReentrantLock[] forks = new ReentrantLock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            philosophers[i].start();
        }
    }
}
