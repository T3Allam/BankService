package accounts;

import exceptions.InsufficientFundsException;
import helpers.SufficientFundsValidator;
import interfaces.IAccount;
import temporaltransactions.TemporalTransactionLinkedList;
import temporaltransactions.TemporalTransactionNode;

import java.lang.Math;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class CreditCard implements IAccount {
    public static double limit = -500.0;
    private double interestRate;
    //Linked list data structure to hold all temporal transactions (account will grow with time based on interest interest)
    private TemporalTransactionLinkedList temporalTransactionLinkedList;

    public CreditCard () {
        this.interestRate = 0.10;
        this.temporalTransactionLinkedList = new TemporalTransactionLinkedList();
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
            SufficientFundsValidator.validateCreditCardTransaction(this, -purchaseTransaction, this.limit);
            //generating random word for vendor
            Random r = new Random();
            String vendor = "" + (char) (r.nextInt(26) + 'A') + (char) (r.nextInt(26) + 'a') + (char) (r.nextInt(26) + 'a') + (char) (r.nextInt(26) + 'a');
            //creating new node to represent transaction
            TemporalTransactionNode temporalTransactionNode = new TemporalTransactionNode(LocalDate.now(), vendor,  - purchaseTransaction);
            this.temporalTransactionLinkedList.append(temporalTransactionNode);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }


    //calculate how debt in credit card
    public double getBalance() {
        //calculating daily interest rate
        double rn = 1+ interestRate/365;
        double balance=0;
        TemporalTransactionNode iterator = temporalTransactionLinkedList.getHead();
        while (iterator.getNext().getDate() != null){
            //conditional statement to make sure we only increase debt for purchase transactions
            if (iterator.getNext().getDollarAmount()<0) {
                //for credit cards, interest is only applied after a grace-period of 21 days
                long period = ChronoUnit.DAYS.between(iterator.getNext().getDate(), LocalDate.now());
                if (period > 21) {
                    balance += iterator.getNext().getDollarAmount() * Math.pow(rn, period);
                } else {
                    balance += iterator.getNext().getDollarAmount();
                }
            } else  { //we reduce debt for payments made by client
                balance += iterator.getNext().getDollarAmount();
            }
            iterator = iterator.getNext();
        }
        return balance;
    }

    public void printTransactions(){
        this.temporalTransactionLinkedList.print();
    }
}