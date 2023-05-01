package org.taxevaders.hokagebet;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static User createAccount() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome. Create an account below:");

            System.out.print("Enter your full name: ");
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
        return input.nextInt();

    }
    public static void main(String[] args) {
        User user1 = createAccount();

    }
}
