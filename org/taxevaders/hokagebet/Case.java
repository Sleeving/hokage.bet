package org.taxevaders.hokagebet;

import java.util.ArrayList;
import java.util.Random;

public class Case extends CasinoGame {
    private String name;
    private ArrayList<Item> items;
    private ArrayList<Double> probabilities;

    public Case() {
        name = null;
        items = null;
        probabilities = null;
    }
    public Case(String name, ArrayList<Item> items, ArrayList<Double> probabilities) {
        this.name = name;
        this.items = items;
        this.probabilities = probabilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Double> getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(ArrayList<Double> probabilities) {
        this.probabilities = probabilities;
    }
    public Item openCase() {
        Random rnd = new Random();
        double randomNumber = rnd.nextDouble();
        double cumulativeProbability = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            cumulativeProbability += probabilities.get(i);
            if (randomNumber <= cumulativeProbability) {
                return items.get(i);
            }
        }
        throw new IllegalStateException("Invalid probabilities configuration.");
    }

    public void playGame() {
    }
}
