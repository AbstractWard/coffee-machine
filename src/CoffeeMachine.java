public class CoffeeMachine {
    private boolean isRunning = true;
    private CoffeeMachineState currentState = CoffeeMachineState.GETTING_ACTION;

    private int waterStored; // In millilitres
    private int milkStored; // In millilitres
    private int coffeeBeansStored; // In grams
    private int cupsStored; // In disposable cups
    private int moneyStored; // In dollars

    public CoffeeMachine() {
        waterStored = 400;
        milkStored = 540;
        coffeeBeansStored = 120;
        cupsStored = 9;
        moneyStored = 550;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    // Takes input and feeds it into the correct methods according to state
    public void processInput(String command) {
        switch (this.currentState) {
            case GETTING_ACTION:
                processAction(command);
                break;
            case GETTING_COFFEE_TYPE:
                processBuy(command);
                break;
            case GETTING_WATER_TO_REFILL:
                processWaterRefill(command);
                break;
            case GETTING_MILK_TO_REFILL:
                processMilkRefill(command);
                break;
            case GETTING_COFFEE_BEANS_TO_REFILL:
                processCoffeeBeanRefill(command);
                break;
            case GETTING_CUPS_TO_REFILL:
                processCupRefill(command);
                break;
        }
    }

    // Gets input for sub-actions and processes actions with no input
    private void processAction(String command) {
        switch (command) {
            case "buy":
                getBuyInput();
                break;
            case "fill":
                getFillInput();
                break;
            case "take":
                processTake();
                break;
            case "remaining":
                processRemaining();
                break;
            case "exit":
                processExit();
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private void getBuyInput() {
        System.out.println("What do you want to buy? (1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu):");
        this.currentState = CoffeeMachineState.GETTING_COFFEE_TYPE;
    }

    private void processBuy(String command) {
        if (!tryParseInt(command)) {
            if (command.equals("back")) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                this.currentState = CoffeeMachineState.GETTING_ACTION;
            } else {
                System.out.println("Invalid command.");
            }
            return;
        }

        int coffeeType = Integer.parseInt(command);

        Coffee espresso = new Coffee(250, 0, 16, 4);
        Coffee latte = new Coffee(350, 75, 20, 7);
        Coffee cappuccino = new Coffee(200, 100, 12, 6);

        switch (coffeeType) {
            case 1:
                checkResourcesAndBuyCoffee(espresso);
                break;
            case 2:
                checkResourcesAndBuyCoffee(latte);
                break;
            case 3:
                checkResourcesAndBuyCoffee(cappuccino);
                break;
        }

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        this.currentState = CoffeeMachineState.GETTING_ACTION;
    }

    private void checkResourcesAndBuyCoffee(Coffee coffee) {
        if (coffee.canMakeCup(this.waterStored, this.milkStored, this.coffeeBeansStored, this.cupsStored)) {
            buyCoffee(coffee);
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            coffee.displayMissingResources(this.waterStored, this.milkStored, this.coffeeBeansStored, this.cupsStored);
        }
    }

    private void buyCoffee(Coffee coffee) {
        this.waterStored -= coffee.getWaterRequired();
        this.milkStored -= coffee.getMilkRequired();
        this.coffeeBeansStored -= coffee.getCoffeeBeansRequired();
        this.cupsStored--;
        this.moneyStored += coffee.getCostPerCup();
    }

    private void getFillInput() {
        System.out.println("Enter how many millilitres of water you want to add:");
        this.currentState = CoffeeMachineState.GETTING_WATER_TO_REFILL;
    }

    private void processWaterRefill(String command) {
        if (!tryParseInt(command)) {
            System.out.println("Invalid command.");
            return;
        }

        this.waterStored += Integer.parseInt(command);
        System.out.println("Enter how many millilitres of milk you want to add:");
        this.currentState = CoffeeMachineState.GETTING_MILK_TO_REFILL;
    }

    private void processMilkRefill(String command) {
        if (!tryParseInt(command)) {
            System.out.println("Invalid command.");
            return;
        }

        this.milkStored += Integer.parseInt(command);
        System.out.println("Enter how many grams of coffee beans you want to add:");
        this.currentState = CoffeeMachineState.GETTING_COFFEE_BEANS_TO_REFILL;
    }

    private void processCoffeeBeanRefill(String command) {
        if (!tryParseInt(command)) {
            System.out.println("Invalid command.");
            return;
        }

        this.coffeeBeansStored += Integer.parseInt(command);
        System.out.println("Enter how many cups you want to add:");
        this.currentState = CoffeeMachineState.GETTING_CUPS_TO_REFILL;
    }

    private void processCupRefill(String command) {
        if (!tryParseInt(command)) {
            System.out.println("Invalid command.");
            return;
        }

        this.cupsStored += Integer.parseInt(command);

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        this.currentState = CoffeeMachineState.GETTING_ACTION;
    }

    private void processTake() {
        System.out.println("I gave you $" + this.moneyStored);
        this.moneyStored = 0;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        this.currentState = CoffeeMachineState.GETTING_ACTION;
    }

    private void processRemaining() {
        System.out.println("----------------------------------------");
        System.out.println("The coffee machine has: ");
        System.out.println(this.waterStored + "mL of water");
        System.out.println(this.milkStored + " mL of milk");
        System.out.println(this.coffeeBeansStored + "g of coffee beans");
        System.out.println(this.cupsStored + " disposable cup(s)");
        System.out.println("$" + this.moneyStored);
        System.out.println("----------------------------------------");

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        this.currentState = CoffeeMachineState.GETTING_ACTION;
    }

    private void processExit() {
        this.isRunning = false;
    }

    private boolean tryParseInt(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }
}
