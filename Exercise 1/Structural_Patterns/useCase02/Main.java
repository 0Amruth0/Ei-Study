interface coffee {
    int cost();
}

class basicCoffee implements coffee {
    @Override
    public int cost() {
        return 10;
    }
}

class milkDecorator implements coffee {
    private coffee coffee;

    public milkDecorator (coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public int cost() {
        return coffee.cost() +5;
    }
}

class sugarDecorator implements coffee {
    private coffee coffee;

    public sugarDecorator(coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public int cost() {
        return coffee.cost() + 2;
    }
}

public class Main {
    public static void main(String[] args) {
        coffee coffee = new basicCoffee();
        coffee = new milkDecorator(coffee);
        coffee = new sugarDecorator(coffee);

        System.out.println("The cost of coffee is : "+ coffee.cost());
    }
}