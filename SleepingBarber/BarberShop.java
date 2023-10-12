import java.util.concurrent.Semaphore;

public class BarberShop {
    private int chairs;
    private Semaphore barberSemaphore;
    private Semaphore customerSemaphore;

    public BarberShop(int chairs) {
        this.chairs = chairs;
        this.barberSemaphore = new Semaphore(0);
        this.customerSemaphore = new Semaphore(1);
    }

    public int getChairs() {
        return this.chairs;
    }

    public void enterShop(Customer customer) {
        try {
            customerSemaphore.acquire();
            if (chairs > 0) {
                chairs--;
                System.out.println(customer + " enters the waiting room. [" + getChairs() +" chairs left]");
                customerSemaphore.release();
                barberSemaphore.release(); // Wake up the barber if sleeping
                customer.getHaircut();
            } else {
                System.out.println("No chairs available. " + customer + " leaves.");
                customerSemaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void leaveShop(Customer customer) {
        System.out.println(customer + " leaves the barber shop.");
        chairs++;
    }

    public void getHaircut(Barber barber) {
        try {
            barberSemaphore.acquire();
            System.out.println(barber + " starts cutting hair.");
            Thread.sleep(1000); // Simulate haircut
            System.out.println(barber + " finishes cutting hair.");
            barberSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
