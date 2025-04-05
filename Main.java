//Multi-threading in java

class Oven {
    private static int availableOvens = 2; // Limited shared resource

    public static synchronized boolean useOven(String chefName) {
        if (availableOvens > 0) {
            availableOvens--;
            System.out.println(chefName + " is using an oven (" + availableOvens + " left)");
            return true;
        }
        System.out.println(chefName + " is waiting (no ovens available)");
        return false;
    }

    public static synchronized void releaseOven() {
        availableOvens++;
    }
}

class Chef extends Thread {
    private String dish;
    
    public Chef(String name, String dish) {
        super(name);
        this.dish = dish;
    }

    public void run() {
        // Try to get an oven
        if (Oven.useOven(getName())) {
            try {
                // Cooking process
                System.out.println(getName() + " is cooking " + dish);
                Thread.sleep(2000 + (int)(Math.random() * 3000)); // Random cooking time
                
                System.out.println(getName() + " finished cooking " + dish);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted!");
            } finally {
                Oven.releaseOven(); // Always release the oven
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Dishes to be cooked
        String[] dishes = {"Pasta", "Pizza", "Salad", "Soup", "Steak", 
                          "Bread", "Dessert", "Coffee"};
        
        // Create chef threads for each dish
        for (int i = 0; i < dishes.length; i++) {
            new Chef("Chef-" + (i+1), dishes[i]).start();
        }

        System.out.println("Kitchen is operational with 2 ovens and " + 
                          dishes.length + " dishes to prepare!");
    }
}
