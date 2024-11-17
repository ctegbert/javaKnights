import java.util.Scanner;

public class Game {
    // ANSI escape codes for colors
    private static final String GREEN = "\033[0;32m";  // Green text
    private static final String RESET = "\033[0m";    // Reset text color

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("Welcome to the RPG Game!");
            
            System.out.print("Enter your character's name: ");
            String playerName = scanner.nextLine();
            
            System.out.println("Choose your class (1: Warrior, 2: Mage): ");
            int classChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character
            String characterClass = (classChoice == 1) ? "Warrior" : "Mage";
            int health = (classChoice == 1) ? 150 : 70;
            int attackPower = (classChoice == 1) ? 15 : 20;

            Player player = new Player(playerName, characterClass, health, attackPower);
            GameStory story = new GameStory();
            story.startStory(player);

            // Battle 1: Bandit Leader
            Enemy banditLeader = new Enemy("Bandit Leader", 80, 10);
            System.out.println(GREEN + "\nA battle has started between " + player.getName() + " and " + banditLeader.getName() + "!" + RESET);
            Battle battle1 = new Battle(player, banditLeader);
            battle1.startBattle();

            if (!player.isAlive()) {
                System.out.println(GREEN + "Game Over!" + RESET);
                gameRunning = promptForRestart(scanner);
                if (!gameRunning) {
                    break;
                }
                continue;
            }

            // Storyline between battles
            System.out.println(GREEN + "\nWith the bandit leader defeated, you have a moment to rest." + RESET);
            System.out.println("Do you wish to continue your quest? (1: Advance, 2: Quit)");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer again
            if (choice != 1) {
                System.out.println("You have chosen to leave the quest. Safe travels!");
                gameRunning = promptForRestart(scanner);
                if (!gameRunning) {
                    break;
                }
                continue;
            }

            // Battle 2: Forest Troll
            Enemy forestTroll = new Enemy("Forest Troll", 100, 15);
            System.out.println(GREEN + "\nYou encounter a " + forestTroll.getName() + "!" + RESET);
            Battle battle2 = new Battle(player, forestTroll);
            battle2.startBattle();

            if (!player.isAlive()) {
                System.out.println(GREEN + "Game Over!" + RESET);
                gameRunning = promptForRestart(scanner);
                if (!gameRunning) {
                    break;
                }
                continue;
            }

            // Choice with a consequence: The chalice step
            System.out.println(GREEN + "\nAfter defeating the troll, you find a mysterious chalice on a pedestal." + RESET);
            System.out.println("Do you drink from the chalice? (1: Yes, 2: No)");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer again
            boolean drankChalice = (choice == 1);

            if (drankChalice) {
                System.out.println(GREEN + "You fool! You fell victim to one of the classic blunders!" + RESET);
            } else {
                System.out.println(GREEN + "You decide to leave the chalice untouched, wary of its potential danger." + RESET);
            }

            // Battle 3: Dragon
            Enemy dragon = new Enemy("Dragon", 150, 20);
            System.out.println(GREEN + "\nA fearsome dragon blocks your path!" + RESET);
            Battle battle3 = new Battle(player, dragon);
            battle3.startBattle();

            if (!player.isAlive()) {
                System.out.println(GREEN + "Game Over!" + RESET);
                gameRunning = promptForRestart(scanner);
                if (!gameRunning) {
                    break;
                }
                continue;
            }

            // Final Battle: Dark Sorcerer
            Enemy darkSorcerer = new Enemy("Dark Sorcerer", 120, 25);
            System.out.println(GREEN + "\nThe final battle against the " + darkSorcerer.getName() + " begins!" + RESET);
            Battle battle4 = new Battle(player, darkSorcerer);
            battle4.startBattle();

            if (!player.isAlive()) {
                System.out.println(GREEN + "Game Over!" + RESET);
                gameRunning = promptForRestart(scanner);
                if (!gameRunning) {
                    break;
                }
                continue;
            }

            // If the player drank the chalice, a final final battle with Cyber Sven occurs
            if (drankChalice) {
                System.out.println(GREEN + "\nAs the Dark Sorcerer falls, the ground shakes and a new threat arises..." + RESET);
                Enemy cyberSven = new Enemy("Cyber Sven", 180, 50);
                System.out.println(GREEN + "\nCyber Sven, the evolved form of the Dark Sorcerer, steps forward!" + RESET);
                Battle battle5 = new Battle(player, cyberSven);
                battle5.startBattle();

                if (player.isAlive()) {
                    System.out.println(GREEN + "Congratulations, " + player.getName() + "! You have defeated Cyber Sven and saved the world!" + RESET);
                } else {
                    System.out.println(GREEN + "You fought valiantly, but Cyber Sven proved too powerful..." + RESET);
                }
            } else {
                System.out.println(GREEN + "Congratulations, " + player.getName() + "! You have defeated the Dark Sorcerer and saved the world!" + RESET);
            }

            // Game completion message
            System.out.println(GREEN + "Thank you for playing!" + RESET);
            gameRunning = promptForRestart(scanner);
        }
        scanner.close();
    }

    public static boolean promptForRestart(Scanner scanner) {
        System.out.print("Do you want to restart the game? (1: Yes, 2: No): ");
        int restartChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after reading the choice
        return restartChoice == 1;
    }
}
