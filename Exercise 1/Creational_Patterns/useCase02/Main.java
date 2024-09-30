interface Animal {
    void speak();
}

class Dog implements Animal {
    @Override
    public void speak(){
        System.out.println("Bow-Wow");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow");
    }
}

class Factory {
    public Animal createAnimal (String animalType){
        if (animalType.equalsIgnoreCase("dog")){
            return new Dog();
        }
        else if (animalType.equalsIgnoreCase("cat")){
            return new Cat();
        }
        return null;
    }
} 
public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Animal animal1 = factory.createAnimal("dog");
        animal1.speak(); 

        Animal animal2 =  factory.createAnimal("cat");
        animal2.speak();
    }
}
