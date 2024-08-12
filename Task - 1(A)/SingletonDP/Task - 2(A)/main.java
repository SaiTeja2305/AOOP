// AbstractFactory.java
public abstract class AbstractFactory {
    public abstract Weapon createWeapon();
    public abstract PowerUp createPowerUp();
}

// App.java
public class App {
    public static void main(String[] args) {
        // Singleton pattern: Manage game state
        GameState gameState = GameState.getInstance();
        gameState.setCurrentLevel(1);
        gameState.setDifficulty("Easy");

        // Factory Method pattern: Create different types of enemies
        EnemyFactory enemyFactory;
        if (gameState.getCurrentLevel() == 1) {
            enemyFactory = new ZombieFactory();
        } else {
            enemyFactory = new VampireFactory();
        }
        Enemy enemy = enemyFactory.createEnemy();
        enemy.attack();

        // Abstract Factory pattern: Create weapons and power-ups
        AbstractFactory levelFactory;
        if (gameState.getDifficulty().equals("Easy")) {
            levelFactory = new EasyLevelFactory();
        } else {
            levelFactory = new HardLevelFactory();
        }
        Weapon weapon = levelFactory.createWeapon();
        PowerUp powerUp = levelFactory.createPowerUp();

        weapon.use();
        powerUp.apply();

        // Update game state
        gameState.setScore(100);
        gameState.setCurrentLevel(2);
        System.out.println("Game State: Level - " + gameState.getCurrentLevel() + ", Score - " + gameState.getScore());
    }
}

// EasyLevelFactory.java
public class EasyLevelFactory extends AbstractFactory {
    @Override
    public Weapon createWeapon() {
        return new Sword();
    }

    @Override
    public PowerUp createPowerUp() {
        return new HealthPack();
    }
}

// Enemy.java
public abstract class Enemy {
    public abstract void attack();
}

// EnemyFactory.java
public abstract class EnemyFactory {
    public abstract Enemy createEnemy();
}

// GameState.java
public class GameState {
    private static GameState instance = null;

    private int currentLevel;
    private int score;
    private String difficulty;

    private GameState() {
        currentLevel = 1;
        score = 0;
        difficulty = "Easy";
    }

    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
            }
        }
        return instance;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

// Gun.java
public class Gun extends Weapon {
    @Override
    public void use() {
        System.out.println("Shooting a gun!");
    }
}

// HardLevelFactory.java
public class HardLevelFactory extends AbstractFactory {
    @Override
    public Weapon createWeapon() {
        return new Gun();
    }

    @Override
    public PowerUp createPowerUp() {
        return new Shield();
    }
}

// HealthPack.java
public class HealthPack extends PowerUp {
    @Override
    public void apply() {
        System.out.println("Applying health pack!");
    }
}

// PowerUp.java
public abstract class PowerUp {
    public abstract void apply();
}

// Shield.java
public class Shield extends PowerUp {
    @Override
    public void apply() {
        System.out.println("Using shield!");
    }
}

// Sword.java
public class Sword extends Weapon {
    @Override
    public void use() {
        System.out.println("Swinging a sword!");
    }
}

// Vampire.java
public class Vampire extends Enemy {
    @Override
    public void attack() {
        System.out.println("Vampire attacks!");
    }
}

// VampireFactory.java
public class VampireFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Vampire();
    }
}

// Weapon.java
public abstract class Weapon {
    public abstract void use();
}

// Zombie.java
public class Zombie extends Enemy {
    @Override
    public void attack() {
        System.out.println("Zombie attacks!");
    }
}

// ZombieFactory.java
public class ZombieFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Zombie();
    }
}
