package accounts;

import exceptions.InsufficientFundsException;
import helpers.NumberGenerator;
import helpers.SufficientFundsValidator;
import interfaces.IAccount;
import temporaltransactions.TemporalTransactionLinkedList;
import temporaltransactions.TemporalTransactionNode;

import java.lang.Math;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class CreditCard implements IAccount {
    private double interestRate;
    private double limit;
    private double balance;
    private int accountNumber;
    //Linked list data structure to hold all temporal transactions (account will grow with time based on interest interest)
    private TemporalTransactionLinkedList temporalTransactionLinkedList;


    public CreditCard (double limit) {
        //setting credit card limit
        this.limit = limit;
        //setting interest rate
        this.interestRate = 0.10;
        //generating unique account number to flag that it's a credit card account number
        this.accountNumber = Integer.parseInt(3 + String.valueOf(NumberGenerator.generateUniqueAccountNumber()));
        this.temporalTransactionLinkedList = new TemporalTransactionLinkedList();
    }

    public int getAccountNumber () {
        return this.accountNumber;
    }

    @Override
    //client making a payment i.e reducing debt there number is positive
    public void deposit(double depositAmount) {
        TemporalTransactionNode temporalTransactionNode = new TemporalTransactionNode(LocalDate.now(), null, depositAmount);
        this.temporalTransactionLinkedList.append(temporalTransactionNode);
    }

    @Override
    //for credit card a withdraw is a purchase from a store and it is debt incurred on client
    public void withdraw(double purchaseTransaction) {
        try {
            SufficientFundsValidator.validateTransaction(this, purchaseTransaction);
            //making amount negative to indicate debt
            double amount = - purchaseTransaction;
            //generating random word for vendor
            String vendor = NumberGenerator.generateRandomWord();
            TemporalTransactionNode temporalTransactionNode = new TemporalTransactionNode(LocalDate.now(), vendor, amount);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    //for the credit card - the balance is (Limit - Total dollar amount of purchases = what remains of credit card that can be used for purchases)
    public double getBalance() {
        double debt = getDebt();
        this.balance = this.limit+debt;
        return this.balance;
    }

    //calculate how debt in credit card
    public double getDebt() {
        //calculating daily interest rate
        double rn = 1+ interestRate/365;
        double debt=0;
        TemporalTransactionNode iterator = temporalTransactionLinkedList.getHead();
        while (iterator.getNext().getDate() != null){
            //conditional statement to make sure we only increase debt for purchase transactions
            if (iterator.getNext().getDollarAmount()<0) {
                //for credit cards, interest is only applied after a grace-period of 21 days
                long period = ChronoUnit.DAYS.between(iterator.getNext().getDate(), LocalDate.now());
                if (period > 21) {
                    debt -= iterator.getNext().getDollarAmount() * Math.pow(rn, period);
                }
                debt -= iterator.getNext().getDollarAmount();
            } else { //we reduce debt for payments made by client
                debt += iterator.getNext().getDollarAmount();
            }
        }
        return debt;
    }
}