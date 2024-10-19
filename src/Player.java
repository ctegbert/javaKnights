class Player extends Character {
    private String characterClass;

    public Player(String name, String characterClass, int health, int attackPower) {
        super(name, health, attackPower);
        this.characterClass = characterClass;
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " attacks " + target.getName() + " for " + attackPower + " damage!");
        target.takeDamage(attackPower);
    }

    public void heal() {
        int healAmount = characterClass.equals("Mage") ? 50 : 20;
        health += healAmount;
        System.out.println(name + " heals for " + healAmount + " health points!");
    }

    public String getCharacterClass() {
        return characterClass;
    }
}
