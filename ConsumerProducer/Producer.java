public class Producer implements Runnable {
    private Value value;

    public Producer(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                value.produce();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
