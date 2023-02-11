package com.code_help.Chrisb;

/**
 * This is my teachers instructions
 * Implement a class Portfolio. This class has two objects, checking and savings, of the type BankAccount that is supplied. Implement four methods:
 * <p>
 * public void deposit(double amount, String account)
 * public void withdraw(double amount, String account)
 * public void transfer(double amount, String account)
 * public double getBalance(String account)
 * Here the account string is "S" or "C". For the deposit or withdrawal, it indicates which account is affected. For a transfer, it indicates the account from which the money is taken; the money is automatically transferred to the other account.
 * <p>
 * 2 classes are provided for you:  BankAccount.java & PortfolioTester.java.
 */
public class PortfolioTester {
    public static void main(String[] args) {
        Portfolio chris = new Portfolio();
        chris.deposit(500, "S");
        chris.deposit(1000, "C");
        chris.withdraw(500, "C");
        chris.transfer(250, "S");
        System.out.println(chris.getBalance("C"));
        System.out.println(chris.getBalance("S"));

        Portfolio megan = new Portfolio();
        megan.deposit(500, "S");
        megan.withdraw(500, "S");
        megan.withdraw(500, "S");
        System.out.println(megan.getBalance("C"));
        System.out.println(megan.getBalance("S"));

    }
}

class Portfolio {
    private BankAccount saving;
    private BankAccount checking;

    public Portfolio() {
        checking = new BankAccount("C", 0.0);
        saving = new BankAccount("S", 0.0);
    }

    public void deposit(double amount, String account) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        BankAccount currentAccount = getAccountFor(account);
        currentAccount.setAmount(currentAccount.getAmount() + amount);
    }

    public void withdraw(double amount, String account) {
        BankAccount currentAccount = getAccountFor(account);
        if (currentAccount.getAmount() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        currentAccount.setAmount(currentAccount.getAmount() - amount);
    }

    public void transfer(double amount, String account) {
        deposit(amount, account);
    }

    public double getBalance(String account) {
        return getAccountFor(account).getAmount();
    }

    private BankAccount getAccountFor(String type) {
        if ("S".equals(type)) {
            return saving;
        } else if ("C".equals(type)) {
            return checking;
        }
        throw new RuntimeException("Check your account type");
    }


}


class BankAccount {
    private String type;
    private double amount;

    public BankAccount(String type, double initialBalance) {
        this.type = type;
        this.amount = initialBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}