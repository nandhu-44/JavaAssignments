public class Main {
    public static void main(String[] args) {
        BarberShop shop = new BarberShop(5); // Number of chairs in the waiting room

        Barber barber = new Barber(shop);
        barber.start();

        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(shop, i + 1);
            customer.start();
        }
    }
}
