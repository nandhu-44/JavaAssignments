public class Consumer implements Runnable {
    private Value value;

    public Consumer(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                value.consume();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
