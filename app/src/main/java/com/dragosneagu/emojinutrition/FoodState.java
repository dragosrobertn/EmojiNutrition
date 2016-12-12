package com.dragosneagu.emojinutrition;

/**
 * Created by dragosneagu on 01/12/2016.
 */

public enum FoodState {
    SOLID("SOLID"),
    SEMI_SOLID("SEMI-SOLID"),
    LIQUID("LIQUID");

    private String state;
    private FoodState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
