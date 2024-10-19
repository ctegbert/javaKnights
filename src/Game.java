import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the RPG Game!");
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        
        System.out.println("Choose your class (1: Warrior, 2: Mage): ");
        int classChoice = scanner.nextInt();
        String characterClass = (classChoice == 1) ? "Warrior" : "Mage";
        int health = (classChoice == 1) ? 150 : 70;
        int attackPower = (classChoice == 1) ? 15 : 20;

        Player player = new Player(playerName, characterClass, health, attackPower);
        GameStory story = new GameStory();
        story.startStory(player);

        // Battle 1: Bandit Leader
        Enemy banditLeader = new Enemy("Bandit Leader", 80, 10);
        System.out.println("\nA battle has started between " + player.getName() + " and " + banditLeader.getName() + "!");
        Battle battle1 = new Battle(player, banditLeader);
        battle1.startBattle();

        if (!player.isAlive()) {
            System.out.println("Game Over!");
            scanner.close();
            return;
        }

        // Storyline between battles
        System.out.println("\nWith the bandit leader defeated, you have a moment to rest.");
        System.out.println("Do you wish to continue your quest? (1: Advance, 2: Quit)");
        int choice = scanner.nextInt();
        if (choice != 1) {
            System.out.println("You have chosen to leave the quest. Safe travels!");
            scanner.close();
            return;
        }

        // Battle 2: Forest Troll
        Enemy forestTroll = new Enemy("Forest Troll", 100, 15);
        System.out.println("\nYou encounter a " + forestTroll.getName() + "!");
        Battle battle2 = new Battle(player, forestTroll);
        battle2.startBattle();

        if (!player.isAlive()) {
            System.out.println("Game Over!");
            scanner.close();
            return;
        }

        // Storyline between battles
        System.out.println("\nWow, that was tough! I hope you recover quickly though, it looks like the final boss is up ahead!!");
        System.out.println("Do you wish to continue your quest? (1: Advance, 2: Quit)");
        choice = scanner.nextInt();
        if (choice != 1) {
            System.out.println("You have chosen to leave the quest. Safe travels!");
            scanner.close();
            return;
        }

        // Battle 3: Dark Sorcerer
        Enemy darkSorcerer = new Enemy("Dark Sorcerer", 120, 20);
        System.out.println("\nThe final battle against the " + darkSorcerer.getName() + " begins!");
        Battle battle3 = new Battle(player, darkSorcerer);
        battle3.startBattle();

        if (player.isAlive()) {
            System.out.println("Congratulations, " + player.getName() + "! You have saved the land!");
        } else {
            System.out.println("You fought bravely, but the darkness prevails...");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
