import java.util.ArrayList;
import java.util.List;


// --- Command Interface ---
interface Command {
    void execute();
}

// --- Rover Class ---
class Rover {
    private int x, y;
    private Direction direction;
    private Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
        Position nextPosition = direction.move(x, y);
        if (grid.isValidPosition(nextPosition)) {
            this.x = nextPosition.getX();
            this.y = nextPosition.getY();
        } else {
            System.out.println("Obstacle or boundary detected. Rover cannot move.");
        }
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public String getStatus() {
        return "Rover is at (" + x + ", " + y + ") facing " + direction.getDirectionName() + ".";
    }
}

// --- Direction Interface ---
interface Direction {
    Direction left();
    Direction right();
    Position move(int x, int y);
    String getDirectionName();
}

// --- Concrete Directions ---
class North implements Direction {
    public Direction left() { return new West(); }
    public Direction right() { return new East(); }
    public Position move(int x, int y) { return new Position(x, y + 1); }
    public String getDirectionName() { return "North"; }
}

class South implements Direction {
    public Direction left() { return new East(); }
    public Direction right() { return new West(); }
    public Position move(int x, int y) { return new Position(x, y - 1); }
    public String getDirectionName() { return "South"; }
}

class East implements Direction {
    public Direction left() { return new North(); }
    public Direction right() { return new South(); }
    public Position move(int x, int y) { return new Position(x + 1, y); }
    public String getDirectionName() { return "East"; }
}

class West implements Direction {
    public Direction left() { return new South(); }
    public Direction right() { return new North(); }
    public Position move(int x, int y) { return new Position(x - 1, y); }
    public String getDirectionName() { return "West"; }
}

// --- Position Class ---
class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

// --- Grid and Obstacle (Composite Pattern) ---
class Grid {
    private int width, height;
    private List<Position> obstacles;

    public Grid(int width, int height, List<Position> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public boolean isValidPosition(Position position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        if (obstacles.contains(position)) {
            return false;
        }
        return true;
    }
}

// --- Command Implementations ---
class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.move();
    }
}

class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnRight();
    }
}

// --- Main Simulation ---
public class Main {
    public static void main(String[] args) {
        // Define the grid size and obstacles
        List<Position> obstacles = new ArrayList<>();
        obstacles.add(new Position(2, 2));
        obstacles.add(new Position(3, 5));
        Grid grid = new Grid(10, 10, obstacles);

        // Initialize the rover
        Rover rover = new Rover(0, 0, new North(), grid);

        // Define commands for the rover
        Command move = new MoveCommand(rover);
        Command turnLeft = new TurnLeftCommand(rover);
        Command turnRight = new TurnRightCommand(rover);

        // Execute commands (example input: M, M, R, M, L, M)
        move.execute();
        move.execute();
        turnRight.execute();
        move.execute();
        turnLeft.execute();
        move.execute();

        // Print status
        System.out.println(rover.getStatus());  // Rover is at (1, 3) facing East.
    }
}
