public class Coffee {
    private final int waterRequired;
    private final int milkRequired;
    private final int coffeeBeansRequired;
    private final int costPerCup;

    public Coffee(int waterRequired, int milkRequired, int coffeeBeansRequired, int costPerCup) {
        this.waterRequired = waterRequired;
        this.milkRequired = milkRequired;
        this.coffeeBeansRequired = coffeeBeansRequired;
        this.costPerCup = costPerCup;
    }

    public boolean canMakeCup(int water, int milk, int coffeeBeans, int cups) {
        boolean hasEnoughWater = water >= this.waterRequired;
        boolean hasEnoughMilk = milk >= this.milkRequired;
        boolean hasEnoughCoffeeBeans = coffeeBeans >= this.coffeeBeansRequired;
        boolean hasEnoughCups = cups >= 1;

        return hasEnoughWater && hasEnoughMilk && hasEnoughCoffeeBeans && hasEnoughCups;
    }

    public void displayMissingResources(int water, int milk, int coffeeBeans, int cups) {
        boolean hasEnoughWater = water >= this.waterRequired;
        boolean hasEnoughMilk = milk >= this.milkRequired;
        boolean hasEnoughCoffeeBeans = coffeeBeans >= this.coffeeBeansRequired;
        boolean hasEnoughCups = cups >= 1;

        if (!hasEnoughWater) {
            System.out.println("Sorry, not enough water!");
        }

        if (!hasEnoughMilk) {
            System.out.println("Sorry, not enough milk!");
        }

        if (!hasEnoughCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        }

        if (!hasEnoughCups) {
            System.out.println("Sorry, not enough cups!");
        }
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }

    public int getCoffeeBeansRequired() {
        return coffeeBeansRequired;
    }

    public int getCostPerCup() {
        return costPerCup;
    }
}
