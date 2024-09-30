import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int temperature);
}

class weatherStation {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class Devices implements Observer {
    String deviceName;
    Devices(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void update(int temperature) {
        System.out.println(this.deviceName + ": Device temperature has been updated. New temperature is: " + temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        weatherStation station = new weatherStation();
        Devices mobile = new Devices("Mobile");
        Devices laptop = new Devices("Laptop");

        station.addObserver(mobile);
        station.addObserver(laptop);

        station.setTemperature(20);
        station.setTemperature(25);
    }
}