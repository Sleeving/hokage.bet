package org.taxevaders.hokagebet;

import java.util.Scanner;

public class User {
    private String name;
    private int age;
    private double balance;
    private double totalWinnings;
    public User() {
        name = null;
        age = 0;
        balance = 0;
        totalWinnings = 0;
    }

    public User(String name, int age, double balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }
    public void addWinnings(double totalWinnings) {
        this.totalWinnings += totalWinnings;
    }
    public double getTotalWinnings() {
        return totalWinnings;
    }

    public boolean removeBalance(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient Balance");
            return false;
        }
    }

    public static void depositBalance(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount you would like to deposit");
        double amount = input.nextDouble();
        user.addBalance(amount);
        System.out.println("Your updated balance is $" + user.getBalance());
    }

    public static double placeBet(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Place a bet $");
        double betAmount = input.nextDouble();

        if(user.removeBalance(betAmount)) {
            System.out.println("Bet Placed, your remaining balance is $" + user.getBalance());
            return betAmount;
        } else {
            System.out.println("Insufficient Balance");
            return -1;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }
}
