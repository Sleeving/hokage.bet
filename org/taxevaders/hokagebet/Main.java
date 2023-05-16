package org.taxevaders.hokagebet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static User createAccount() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome. Create an account below:");

            System.out.print("Enter your username: ");
            String name = input.nextLine();

            System.out.print("Enter your age: ");
            int age = input.nextInt();
            if (age < 18)
                throw new InputMismatchException();

            System.out.print("Enter amount of balance to deposit: ");
            double balance = input.nextDouble();

            return new User(name, age, balance);

        } catch (InputMismatchException e) {
            System.out.println("Invalid entry.");
            System.out.println();
            return createAccount();
        }
    }

    public static int mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Hokage.bet. Choose a game below:");
        System.out.println("1. Blackjack");
        System.out.println("2. Case Opening");
        System.out.println("3. Dice");
        System.out.println("4. Set balance");

        return input.nextInt();
    }

    public static double caseProbability(Item.Rarity rarity) {
        return switch (rarity) {
            case LEGENDARY -> 0.26;
            case COVERT -> 0.64;
            case CLASSIFIED -> 3.2;
            case RESTRICTED -> 15.98;
            case MILSPEC -> 79.92;
            default -> throw new IllegalArgumentException("Unknown Rarity.");
        };
    }

    public static ArrayList<Case> setUpCases() {
        // Create items
        ArrayList<Item> items1 = new ArrayList<>();
        Item karambit = new Item("Karambit | Marble Fade", Item.Rarity.LEGENDARY, Item.ItemType.KNIFE);
        Item fireSerpent = new Item("AK-47 | Fire Serpent", Item.Rarity.COVERT, Item.ItemType.GUN);
        Item targetAcquired = new Item("USP-S | Target Acquired", Item.Rarity.CLASSIFIED, Item.ItemType.GUN);
        Item dragonTattoo = new Item("Glock-18 | Dragon Tattoo", Item.Rarity.RESTRICTED, Item.ItemType.GUN);
        Item safariMesh = new Item("AWP | Safari Mesh", Item.Rarity.MILSPEC, Item.ItemType.GUN);
        items1.add(karambit);
        items1.add(fireSerpent);
        items1.add(targetAcquired);
        items1.add(dragonTattoo);
        items1.add(safariMesh);

        ArrayList<Item> items2 = new ArrayList<>();
        Item viceGloves = new Item("Sport Gloves | Vice", Item.Rarity.LEGENDARY, Item.ItemType.GLOVES);
        Item dragonLore = new Item("AWP | Dragon Lore", Item.Rarity.COVERT, Item.ItemType.GUN);
        Item caseHardened = new Item("AK-47 | Case Hardened", Item.Rarity.CLASSIFIED, Item.ItemType.GUN);
        Item sunsetStorm = new Item("Desert Eagle | Sunset Storm", Item.Rarity.RESTRICTED, Item.ItemType.GUN);
        Item sandDune = new Item("P250 | Sand Dune", Item.Rarity.MILSPEC, Item.ItemType.GUN);
        items2.add(viceGloves);
        items2.add(dragonLore);
        items2.add(caseHardened);
        items2.add(sunsetStorm);
        items2.add(sandDune);


        // Set probabilities
        ArrayList<Double> probabilities = new ArrayList<>();
        for (Item item : items1) {
            double probability1 = caseProbability(item.getRarity());
            probabilities.add(probability1);
        }
        for (Item item : items2) {
            double probability2 = caseProbability(item.getRarity());
            probabilities.add(probability2);
        }

        // Create the case
        Case case1 = new Case("Bravo Case", items1, probabilities);
        Case case2 = new Case("Glove Case", items2, probabilities);

        // Add more cases if needed
        ArrayList<Case> cases = new ArrayList<>();
        cases.add(case1);
        cases.add(case2);

        return cases;
    }

    public static int chooseCase(ArrayList<Case> cases, User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose a case to open:");
        for (int i = 0; i < cases.size(); i++) {
            System.out.println((i + 1) + ". " + cases.get(i).getName());
        }
        System.out.println("0. Go back");

        int caseIndex = input.nextInt() - 1;

        // Check if user has enough balance to open the case
        if (user.getBalance() < 3.0) {
            System.out.println("Insufficient balance to open a case. Please deposit more money.");
            return -1;
        }

        if (caseIndex >= 0 && caseIndex < cases.size()) {
            // Deduct the balance
            user.setBalance(user.getBalance() - 3.0);
        }

        return caseIndex;
    }

    public static void main(String[] args) throws IOException {
        User user = createAccount();
        ArrayList<Case> cases = setUpCases();
        int gameChoice;

        do {
            gameChoice = mainMenu();
            switch (gameChoice) {
                case 1:
                    BlackJack blackJackGame = new BlackJack(user);
                    blackJackGame.playGame();
                    break;
                case 2:
                    int caseIndex = chooseCase(cases, user);
                    if (caseIndex >= 0 && caseIndex < cases.size()) {
                        Case selectedCase = cases.get(caseIndex);
                        selectedCase.openCase(user);
                        System.out.println("Total winnings so far: " + user.getTotalWinnings());
                    } else {
                        System.out.println("Invalid case selection. Try again.");
                    }
                    break;
                case 3:
                    Dice diceGame = new Dice(user);
                    diceGame.playGame();
                    break;
                case 4:
                    Scanner input = new Scanner(System.in);
                    user.setBalance(input.nextDouble());
                    System.out.println("Balance was set to: $" + user.getBalance());
                    break;
                default:
                    System.out.println("Invalid choice. Try again");
                    break;
            }
        } while (gameChoice != 0);
    }
}
