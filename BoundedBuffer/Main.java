public class Main {
    public static void main(String[] args) {
        int limit = 5;
        Buffer buffer = new Buffer(limit);
        new Thread(new Adder(buffer), "Adder").start();
        new Thread(new Remover(buffer), "Remover").start();
    }
}
