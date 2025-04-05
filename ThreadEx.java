//Multi-threading in java

class Oven {
    synchronized static void use(String chef, String dish) {
        System.out.println(chef + " is baking " + dish);
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(chef + " finished " + dish);
    }
}

class Chef extends Thread {
    private String dish;
    Chef(String name, String dish) {
        super(name);
        this.dish = dish;
    }
    public void run() {
        Oven.use(getName(), dish);
    }
}
public class ThreadEx {
    public static void main(String[] args) {
        new Chef("Chef A", "Pizza").start();
        new Chef("Chef B", "Burger").start();
    }
}
