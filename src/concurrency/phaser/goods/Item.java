package concurrency.phaser.goods;

/**
 * Перевозимый товар.
 */
public class Item {
    private int registrationNumber;

    public Item(int number) {
        this.registrationNumber = number;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }
}
