public class Customer extends Thread {
    private BarberShop shop;
    private int customerNumber;

    public Customer(BarberShop shop, int customerNumber) {
        this.shop = shop;
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber(){
        return this.customerNumber;
    }

    @Override
    public void run() {
        shop.enterShop(this);
        shop.leaveShop(this);
    }

    @Override
    public String toString() {
        return "Customer " + this.getCustomerNumber();
    }

    public void getHaircut() {
        System.out.println(this + " gets a haircut.");
    }
}
