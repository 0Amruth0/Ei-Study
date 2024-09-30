interface Command {
    void execute();
    void undo();
}

class AC {
    public int temperature;

    public void on() {
        System.out.println("The AC is on and is currently set to " + temperature + " degree celcius.");
    }

    public void off() {
        System.out.println("The AC is now off.");
    }

    public void increment() {
        temperature++;
        System.out.println("The AC temperature is now set to " + temperature + " degree celcius.");
    }

    public void decrement() {
        temperature--;
        System.out.println("The AC temperature is now set to " + temperature + " degree celcius.");
    }
}

class AcOnCommand implements Command {
    private AC ac;

    public AcOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class AcOffCommand implements Command {
    private AC ac;

    public AcOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}

class AcIncrementCommand implements Command {
    private AC ac;
    private int previousTemperature;

    public AcIncrementCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        previousTemperature = ac.temperature;
        ac.increment();
    }

    @Override
    public void undo() {
        ac.decrement();
        ac.temperature = previousTemperature;
    }
}

class AcDecrementCommand implements Command {
    private AC ac;
    private int previousTemperature;

    public AcDecrementCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        previousTemperature = ac.temperature;
        ac.decrement();
    }

    @Override
    public void undo() {
        ac.increment();
        ac.temperature = previousTemperature;
    }
}

class RemoteControl {
    private Command lastCommand;

    public void pressButton(Command command) {
        this.lastCommand = command;
        command.execute();
    }

    public void pressUndo() {
        lastCommand.undo();
    }
}

public class Main {
    public static void main(String[] args) {
        AC ac = new AC();
        AcOnCommand onCommand = new AcOnCommand(ac);
        AcOffCommand offCommand = new AcOffCommand(ac);
        AcIncrementCommand incrementCommand = new AcIncrementCommand(ac);
        AcDecrementCommand decrementCommand = new AcDecrementCommand(ac);

        RemoteControl remote = new RemoteControl();
        remote.pressButton(onCommand);
        remote.pressButton(incrementCommand);
        remote.pressUndo();
        remote.pressButton(decrementCommand);
        remote.pressUndo();
        remote.pressButton(offCommand);
        remote.pressUndo();
    }
}