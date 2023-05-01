package org.taxevaders.hokagebet;

import java.util.Scanner;
public class Main {
    public static User createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome. Create an account below:");
        System.out.print("Enter your full name: ");
        String name = input.nextLine();
        System.out.print("Enter your age: ");
        int age = input.nextInt();
        System.out.print("Enter amount of balance to deposit: ");
        double balance = input.nextDouble();

        return new User(name, age, balance);
    }
    public static int mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Hokage.bet. Choose a game below:");
        System.out.println("1. Blackjack");
        System.out.println("2. Case Opening");
        return input.nextInt();

    }
    public static void main(String[] args) {
        User user = createAccount();

    }
}
