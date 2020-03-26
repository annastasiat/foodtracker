package ua.training.foodtracker.entity;

public enum Gender {
    FEMALE(-161),
    MALE(5);

    private int value;

    Gender(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
