package org.taxevaders.hokagebet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Case {
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

    public void openCase(User user) {
        // Check if the user has enough balance
        if(user.getBalance() < 3) {
            System.out.println("Not enough balance. Please deposit more money.");
            return;
        }

        // Deduct the cost of opening a case from the user's balance
        user.setBalance(user.getBalance() - 3);

        // Open the case and get a random item
        Random rnd = new Random();
        double randomNumber = rnd.nextDouble();
        double cumulativeProbability = 0;
        Item unboxedItem = null;

        for (int i = 0; i < probabilities.size(); i++) {
            cumulativeProbability += probabilities.get(i);
            if (randomNumber <= cumulativeProbability) {
                unboxedItem = items.get(i);
                break;
            }
        }

        if (unboxedItem == null) {
            throw new IllegalStateException("Invalid probabilities configuration.");
        }

        // Calculate the winnings based on the rarity
        double winnings = 0;
        switch(unboxedItem.getRarity()) {
            case MILSPEC:
                winnings = 0.03 * 10;
                break;
            case RESTRICTED:
                winnings = 0.03 * 10 * 10;
                break;
            case CLASSIFIED:
                winnings = 0.03 * 10 * 10 * 10;
                break;
            case COVERT:
                winnings = 0.03 * 10 * 10 * 10 * 10;
                break;
            case LEGENDARY:
                winnings = 0.03 * 10 * 10 * 10 * 10 * 10;
                break;
            default:
                break;
        }

        // Add the winnings to the user's balance
        user.setBalance(user.getBalance() + winnings);
        user.addWinnings(winnings);

        // Export the winnings to a text file
        try (PrintWriter out = new PrintWriter(new FileWriter("winnings.txt", true))) {
            out.println("User: " + user.getName() + ", Unboxed: " + unboxedItem.getName() + ", Winnings: $" + winnings);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }

        System.out.println("You unboxed: " + unboxedItem.getName() + " (Rarity: " + unboxedItem.getRarity() + ")");
        System.out.println("You won: $" + winnings);
    }
}
