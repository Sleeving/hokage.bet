package org.taxevaders.hokagebet;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main  {
    public static User createAccount() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome. Create an account below:");

            System.out.print("Enter your username: ");
            String name = input.nextLine();

            System.out.print("Enter your age: ");
            int age = input.nextInt();
            if(age < 18)
                throw new InputMismatchException();

            System.out.print("Enter amount of balance to deposit: ");
            double balance = input.nextDouble();

            return new User(name, age, balance);

        } catch(InputMismatchException e) {
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
    public static ArrayList<Case> setUpCases() {
        // Create items
        ArrayList<Item> items = new ArrayList<>();
        Item knife = new Item("Knife", Item.Rarity.LEGENDARY, Item.ItemType.KNIFE);
        Item gun = new Item("Gun", Item.Rarity.MILSPEC, Item.ItemType.GUN);
        items.add(knife);
        items.add(gun);

        // Set probabilities
        ArrayList<Double> probabilities = new ArrayList<>();
        double knifeProbability = 1.0 / 400;
        double gunProbability = 1 - knifeProbability;
        probabilities.add(knifeProbability);
        probabilities.add(gunProbability);

        // Create the case
        Case caseExample = new Case("Example Case", items, probabilities);

        // Add more cases if needed
        ArrayList<Case> cases = new ArrayList<>();
        cases.add(caseExample);

        return cases;
    }
    public static int chooseCase(ArrayList<Case> cases) {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose a case to open:");
        for (int i = 0; i < cases.size(); i++) {
            System.out.println((i + 1) + ". " + cases.get(i).getName());
        }
        System.out.println("0. Go back");

        return input.nextInt() - 1;
    }
    public static void main(String[] args) {
        User user = createAccount();
        ArrayList<Case> cases = setUpCases();
        int gameChoice;

        do {
            gameChoice = mainMenu();
            switch(gameChoice) {
                case 1:
                    System.out.println("blackjack");
                    break;
                case 2:
                    int caseIndex = chooseCase(cases);
                    if (caseIndex >= 0 && caseIndex < cases.size()) {
                        Case selectedCase = cases.get(caseIndex);
                        Item unboxedItem = selectedCase.openCase();
                        System.out.println("You unboxed: " + unboxedItem.getName() + " (Rarity: " + unboxedItem.getRarity() + ")");
                    } else {
                        System.out.println("Invalid case selection. Try again.");
                    }
                    break;
                case 3:

                    break;
                case 4:
                    Scanner input = new Scanner(System.in);
                    user.setBalance(input.nextDouble());
                    break;
                default:
                    System.out.println("invalid choice. try again");
                    break;
            }
        } while (gameChoice != 0);
    }
}
