public class Barber extends Thread {
    private BarberShop shop;

    public Barber(BarberShop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            shop.getHaircut(this);
        }
    }

    @Override
    public String toString() {
        return "Barber";
    }
}
