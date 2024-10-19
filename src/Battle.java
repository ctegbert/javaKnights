import java.util.Scanner;
import java.util.Random;

class Battle {
    private Player player;
    private Enemy enemy;
    private Random random;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.random = new Random();
    }

    public void startBattle() {
        Scanner scanner = new Scanner(System.in);

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n" + player.getName() + "'s Health: " + player.getHealth());
            System.out.println(enemy.getName() + "'s Health: " + enemy.getHealth());
            System.out.println("What will you do? (1: Attack, 2: Heal)");
            int choice = scanner.nextInt();

            if (choice == 1) {
                player.attack(enemy);
            } else if (choice == 2) {
                player.heal();
            } else {
                System.out.println("Invalid choice!");
                continue;
            }

            // Enemy automatically attacks if still alive
            if (enemy.isAlive()) {
                enemy.attack(player);
            }
        }

        if (player.isAlive()) {
            System.out.println("\n" + player.getName() + " has defeated " + enemy.getName() + "!");
        } else {
            System.out.println("\n" + player.getName() + " has been defeated by " + enemy.getName() + "...");
        }
    }
}
